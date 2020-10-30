# poller

## Team Information


| Name | NEU ID | Email Address |
| --- | --- | --- |
| Arundathi Patil     | 001066930 | patil.aru@northeastern.edu |
| Harrsini Sekar      | 001344424 | sekar.h@northeastern.edu   |
| Keshav Kaanth Kumar | 001054298 | kumar.ke@northeastern.edu  |
 
Build Instructions
Setting up Jenkins 
* Open your domain where Jenkins is hosted
* Login to Jenkins console using the steps mentioned on the console
* Download the plugins. Make sure github and docker plugins are installed
* Click new to create a new job.
* Select Pipeline and provide a name for your job.
* Select "GitHub hook trigger for GITScm polling" in Build Triggers
* Select Pipeline script from scm in Pipeline Defination
* Select Git in SCM
* Add the repository details. Add the credentials
* Provide the path of Jenkinsfile "./jenkinsfile"
* Apply and Save the path
* Now add the environment variables:
* - Add Docker credentials {DOCKER_USER} and {DOCKER_PASS} for pushing the image tto docker hub
* - Add {POLLER_IMAGE_NAME} for the docker image name

Setting the github 
* Open the github repository and add the webhook for the Jenkins server under settings>webhooks option
* Provide the payload url(url where jenkins is hosted) and append /github-webhook/ in the end. Example: jenkins.harrsinisekar.me
* Content type: application/json
* Save the Webhook

Triggering the job 
* Push the code to the repository.
* This should trigger the job in Jenkins.
* Once completed, a new docker image should be available at docker hub .
