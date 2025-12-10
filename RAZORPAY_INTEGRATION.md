# Razorpay Payment Integration - Art Shop

## Overview
This document describes the Razorpay payment gateway integration in the Art Shop e-commerce platform.

## Configuration

### Razorpay Credentials
The following test credentials are configured in `application.properties`:

```properties
razorpay.key.id=rzp_test_RbJGciJT7ShcMq
razorpay.key.secret=As10u7fceTnbSkslMJnLcqQk
```

⚠️ **Important**: These are TEST credentials. For production, replace with live credentials from your Razorpay dashboard.

## Features Implemented

### 1. Cart Page Payment Integration
- **File**: `backend/src/main/resources/static/cart.html`
- Razorpay checkout button replaces traditional card input
- Payment amount automatically calculated from cart total
- Form validation before payment initiation

### 2. Payment Flow
1. User adds items to cart
2. User fills in delivery details (name, email, address, city, postal code)
3. User clicks "Pay with Razorpay" button
4. Razorpay modal opens with payment options:
   - Credit/Debit Cards
   - Net Banking
   - UPI
   - Wallets
5. After successful payment:
   - Order is created in database
   - Cart is cleared
   - User redirected to order confirmation page

### 3. Payment Details Stored
- Payment ID from Razorpay is stored in the `cardNumber` field
- Order status set to 'PAID'
- Full order details saved to MongoDB

## How It Works

### Frontend Integration

```javascript
// Razorpay script loaded in HTML head
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>

// Payment initialization
const options = {
    key: 'rzp_test_RbJGciJT7ShcMq',
    amount: amountInPaise, // Amount in smallest currency unit
    currency: 'INR',
    name: 'Art Shop',
    description: 'Artwork Purchase',
    handler: function(response) {
        // Handle successful payment
        handlePaymentSuccess(response, orderData);
    },
    prefill: {
        name: fullName,
        email: email
    },
    theme: {
        color: '#667eea'
    }
};

const rzp = new Razorpay(options);
rzp.open();
```

### Payment Success Handler
```javascript
async function handlePaymentSuccess(paymentResponse, orderData) {
    // Create order with payment ID
    const order = {
        ...orderData,
        cardNumber: 'Razorpay: ' + paymentResponse.razorpay_payment_id,
        status: 'PAID'
    };
    
    // Submit to backend
    await fetch('/api/orders', {
        method: 'POST',
        body: JSON.stringify(order)
    });
    
    // Clear cart and redirect
    localStorage.removeItem('cart');
    window.location.href = 'order-confirmation.html?orderId=' + savedOrder.id;
}
```

## Testing

### Test Cards (Razorpay Test Mode)
Use these test card details in test mode:

**Success:**
- Card Number: `4111 1111 1111 1111`
- CVV: Any 3 digits
- Expiry: Any future date

**Failure:**
- Card Number: `4000 0000 0000 0002`

### Test UPI
- UPI ID: `success@razorpay`

### Test Net Banking
- Select any bank
- Use credentials provided on test page

## Currency
Currently configured for **INR (Indian Rupees)**. 

To change currency:
1. Update `currency: 'INR'` in the Razorpay options
2. Ensure your Razorpay account supports the currency
3. Update price display format in frontend

## Amount Conversion
Razorpay requires amount in smallest currency unit (paise for INR):
```javascript
const amountInPaise = Math.round(totalAmount * 100);
```

## Security Notes

1. **Never expose key_secret in frontend code** - Only `key_id` is used in frontend
2. **key_secret** should only be used in backend for payment verification
3. For production:
   - Use environment variables for credentials
   - Implement payment signature verification
   - Add webhook handlers for payment status updates

## Production Checklist

Before going live:

- [ ] Replace test credentials with live credentials
- [ ] Implement payment signature verification on backend
- [ ] Set up Razorpay webhooks for payment notifications
- [ ] Add proper error handling and logging
- [ ] Test with real payment methods
- [ ] Implement refund functionality
- [ ] Add payment receipt generation
- [ ] Set up SSL certificate (HTTPS required)
- [ ] Configure proper CORS settings
- [ ] Add rate limiting for payment endpoints

## API Endpoints

### Create Order
```
POST /api/orders
Content-Type: application/json

{
    "fullName": "John Doe",
    "email": "john@example.com",
    "address": "123 Main St",
    "city": "Mumbai",
    "postalCode": "400001",
    "cardNumber": "Razorpay: pay_xxxxxxxxxxxxx",
    "items": [...],
    "totalAmount": 1500.00,
    "status": "PAID"
}
```

## Troubleshooting

### Payment Modal Not Opening
- Check if Razorpay script is loaded: `<script src="https://checkout.razorpay.com/v1/checkout.js"></script>`
- Verify key_id is correct
- Check browser console for errors

### Payment Success But Order Not Created
- Check network tab for API call failures
- Verify backend is running
- Check MongoDB connection
- Review browser console logs

### Amount Mismatch
- Ensure amount is converted to paise (multiply by 100)
- Use `Math.round()` to avoid decimal issues

## Support

For Razorpay-specific issues:
- Documentation: https://razorpay.com/docs/
- Support: https://razorpay.com/support/

For Art Shop integration issues:
- Check browser console
- Review server logs
- Contact: support@artshop.com

## Future Enhancements

1. Add payment verification on backend
2. Implement webhook handlers
3. Add subscription payments for premium features
4. Support multiple currencies
5. Add payment analytics dashboard
6. Implement automatic refunds
7. Add payment retry mechanism
8. Generate PDF invoices

---

**Last Updated**: November 28, 2025
**Version**: 1.0
