package org.example;

import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.values.PCollection;

public class DataflowPipeline {
    public static void main(String[] args) {
        // Create PipelineOptions
        DataflowPipelineOptions options = PipelineOptionsFactory.as(DataflowPipelineOptions.class);
        options.setProject("dualpubsub");
        options.setRegion("europe-west1");
        options.setStagingLocation("gs://duo-pub-sub-bucket/output/");
        options.setGcpTempLocation("gs://duo-pub-sub-bucket/temp/");
        options.setNetwork("default");
        options.setSubnetwork("regions/us-central1/subnetworks/default");

        options.setRunner(DataflowRunner.class);

        // Create the pipeline
        Pipeline pipeline = Pipeline.create(options);

        // Define the Pub/Sub subscription and destination topics
        String subscription = "projects/dualpubsub/subscriptions/order_source_topic-sub";
        String destinationTopic1 = "projects/dualpubsub/topics/oder_destination_topic1";
        String destinationTopic2 = "projects/dualpubsub/topics/oder_destination_topic2";

        // Read from the Pub/Sub subscription
        PCollection<String> messages = pipeline.apply("Read from Pub/Sub", PubsubIO.readStrings().fromSubscription(subscription));

        // Write to the destination topics
        messages.apply("Write to Destination Topic 1", PubsubIO.writeStrings().to(destinationTopic1));
        messages.apply("Write to Destination Topic 2", PubsubIO.writeStrings().to(destinationTopic2));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
