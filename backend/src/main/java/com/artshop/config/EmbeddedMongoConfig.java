package com.artshop.config;

import jakarta.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Storage;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.File;
import java.io.IOException;

@Configuration
@Profile("dev")
public class EmbeddedMongoConfig {

    private MongodExecutable mongodExecutable;
    private MongodProcess mongodProcess;

    @Bean
    public MongodExecutable embeddedMongod() throws IOException {
        // Use permanent storage in mongodb-data folder
        String dataPath = new File("mongodb-data").getAbsolutePath();
        System.out.println("MongoDB data will be stored in: " + dataPath);
        
        MongodStarter starter = MongodStarter.getDefaultInstance();
        
        MongodConfig mongodConfig = MongodConfig.builder()
                .version(Version.Main.PRODUCTION)
                .net(new de.flapdoodle.embed.mongo.config.Net(27017, Network.localhostIsIPv6()))
                .replication(new Storage(dataPath, null, 0))
                .build();

        mongodExecutable = starter.prepare(mongodConfig);
        mongodProcess = mongodExecutable.start();
        
        System.out.println("✅ MongoDB started with PERMANENT storage!");
        System.out.println("✅ All data will persist across restarts!");
        
        return mongodExecutable;
    }

    @PreDestroy
    public void stop() {
        if (mongodProcess != null) {
            mongodProcess.stop();
        }
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }
}
