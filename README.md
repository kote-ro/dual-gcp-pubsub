# Task
Create a Java dataflow pipeline which reads data from one pub/sub subscription and copies it into two pub/sub topics.
# How to run
1. Go to [this](https://cloud.google.com/dataflow/docs/quickstarts/create-pipeline-java) tutorial in GCP docs and pass first 12 points 
2. Use one of two scripts, depending on your Operating System:
- For Windows users
```PowerShell
mvn clean compile exec:java 
  -P dataflow-runner 
  -D exec.mainClass=org.example.DataflowPipeline 
  -D exec.args="--project=PROJECT_ID 
  --gcpTempLocation=gs://BUCKET_NAME/temp/ 
  --output=gs://BUCKET_NAME/output 
  --runner=DataflowRunner 
  --region=REGION"
```
- For Linux users
```Shell
mvn -Pdataflow-runner clean compile exec:java \
    -Dexec.mainClass=org.apache.beam.examples.WordCount \
    -Dexec.args="--project=PROJECT_ID \
    --gcpTempLocation=gs://BUCKET_NAME/temp/ \
    --output=gs://BUCKET_NAME/output \
    --runner=DataflowRunner \
    --region=REGION"
```
Paste this variables for your environment:

* `PROJECT_ID` : your Google Cloud project ID

* `BUCKET_NAME` : the name of your Cloud Storage bucket

* `REGION` : a Dataflow regional endpoint, like us-central1
