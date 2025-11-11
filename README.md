
Ansible:




 vi /etc/ssh/sshd_config

→ Line no 38 change PermitRootLogin to yes



→ Line no 65 change the password authentication from no to yes




→ RESTART SSHD (systemctl restart sshd) 

→ vim ansible.cfg 

→ add the below script in ansible.cfg file

[defaults]
inventory = /etc/ansible/hosts
host_key_checking = False
retry_files_enabled = False



→ vim hosts

Add the slave server Private IP's in the inventory (hosts)

[root@master ansible]# cat hosts

[dev]
172.31.41.203
[qa]
172.31.33.226



→ ssh-keygen

→ ssh-copy-id root@<slave-server-private-IP>

→ ansible all -m ping







Slave server configuration changes

1) Set root password
2) Change /etc/ssh/sshd_config file
3) Restart sshd service








command to execute the playbook: ansible-playbook <filename.yaml>



On slave server Git is installed



Implementing variables in playbook







Tree package:





Command for Passing variables in runtime  >> ansible-playbook --extra-vars "tool=tree"




Ansible playbook for adding user





Ansible Playbook for creating multiple users







Deleting the users :


























→ Implementing when condition



Implementing Tags & skip particular task

ansible-playbook filename.yaml --skip-tags=tagname



Execute only particular task for the playbook

ansible-playbook filename.yaml --tags=tagname



Handlers concept in ansible:


How Handlers Work — Step-by-Step Flow
	1. A task runs — for example, updating a configuration file using the template or copy module.
	2. If that task changes something, it triggers a notification to one or more handlers.
	3. Handlers are queued, not run immediately.
	4. At the end of the play, all notified handlers are run once, even if multiple tasks notified the same handler.
	5. If no changes occurred → the handler is not triggered.













Ansible playbook to launch Tomcat server



Ansible playbook to launch Tomcat server using script










Ansible playbook for cloning code from github



Ansible Vault



Ansible vault encryption and decryption



Changing Ansible vault Password




ansible-vault encrypt file
ansible-vault decrypt file
ansible-vault rekey file
ansible-vault view file

ansible vault create <filename>








Roles





---
- hosts: localhost
  roles:
    - jai
    - sai
    - kavi
    - ram

- name: create httpd
  yum: name=httpd state=present

- name: start httpd service
  service: name=httpd state=started
   
- name: create a file
  file:
    path: "/var/www/html/index.html"
    state: touch

- name: adding content
  copy:
    dest: "/var/www/html/index.html"
    content: |
      <h1>This is Ansible Roles</h1>  



Ad-Hoc Commands:





ansible dev -m copy -a "src=main.yml dest=/root"

ansible dev,qa -m command -a "lsblk"

ansible all -m command -a "df -h"

ansible all -m command -a "git -v"




Installing python pip and NumPy packages




Implementing Jenkins slave concept using Ansible Playbook :




Difference between Git Merge & Git Rebase




Docker:




Virtual Machine Stack : Each VM has its own kernel.

+---------------------------------------+                                     
| Application                                |
+---------------------------------------+
| Guest OS (Ubuntu,Linux)        |
+---------------------------------------+
| Virtual Hardware                      |
+---------------------------------------+
| Hypervisor (KVM,VMware)    |
+---------------------------------------+
| Host OS + Physical Hardware |
+---------------------------------------+


Container Stack: All containers share the same kernel.

+--------------------------------------------+
| Application                                      |
+--------------------------------------------+
| Container Runtime (Docker)         |
+--------------------------------------------+
| Shared OS Kernel                            |
+--------------------------------------------+
| Host OS + Physical Hardware        |
+--------------------------------------------+

Containers are nothing but virtual machines which don’t have an operating system

Containers are lightweight, isolated user-space environments that share the host operating system kernel, unlike virtual machines which include a full operating system.


EC2 : AMI (OS) ---- > CHANGE ---- > APP RUN

DOCKER : IMAGES --- > CHANGE --- > OWN IMAGE --- > CONTAINER (app)

DOCKER BASE IMAGE ---- > MODIFY (Docker file) ---- > OWN IMAGE --- > RUN ---- > CONTAINER (app)

Manam Docker image ni run chesthey Container create avuthundi



















Port are used to make a networking connection between two systems or two services or through the Internet

Between two systems or between services oka communication ni establish cheyale antey port kavali










Docker commands:

docker run -itd -- name cont1 -p 1234:80 nginx
docker ps
docker ps -a
docker ps -a -f status="exited"  (-f is filter)
docker exec -it cont-name bash
docker inspect cont-name
docker stop cont-name
docker stop $(docker ps -a)
docker start cont-name
docker start $(docker ps -a)
docker rm cont-name
docker rm -f cont-name
docker rm $(docker ps -a)
docker inspect cont-name
Docker inspect Image-name
docker build -t <imagename> .



Dockerfile:
. It is a text file which contains some set of instructions to run the application
· It automates the process of creating images.
. In Dockerfile "D" must be capital
. It has different components

1. FROM : used to define the base images [ Ex: Ubuntu, Nginx, Tomcat, MySQL, Python]
2. COPY : Used to copy the files from Host to Container.
3. ADD : Used to copy the files from Host to Container and also it will download the files from Internet and send those files to containers.
4. WORKDIR : used to set a default path in container [WORKDIR/DEVOPS/APP]
5. RUN : Used to execute the commands while we creating the image
6. CMD : Used to execute the commands while we creating the container
7. ENTRYPOINT : Used to execute the commands while creating the container (High priority)
8. ARG : Used to pass the variables, these variables we can't access inside the containers
9. ENV : Used to pass the variables, these variables we can access inside the containers
10. USER : Used to run the commands through the separate user.
11. EXPOSE : Used to publish the port number.
12. LABEL : Used to define the description of the image
13. MAINTAINER : Used to provide author details of Dockerfile


Run command image create cheseina appudu use chestamu
Cmd commnad container create cheseina appudu use chestamu







vim Dockerfile

docker build -t imagename .

docker run -itd --name contname imagename

docker exec -it contname bash
















Step 1: Pull application source code from GitHub it contains pom.xml file & src (application source code file)

Step 2: Build the Java Application using Maven

Purpose:
Cleans old builds and compiles the new app.
Generates an executable file inside the target/ directory:
If it’s a web app → myweb.war
If it’s a standalone Java app → myweb.jar

mvn clean package
Output Example:
target/myweb-1.0.war


Step 3: Create a Dockerfile (Container Blueprint)
Now, create a file named Dockerfile (no extension) in the project root.

Dockerfile:
FROM tomcat:8.0.20-jre8
COPY tomcat-users.xml /usr/local/tomcat/conf
COPY target/*.war /usr/local/tomcat/webapps/myweb.war
EXPOSE 8081


Step 4: Build Docker Image
docker build -t image-name .

Step 5: Run Container

docker run --name cont-name -p 8081:8080 image-name

Reference link : https://docker77.hashnode.dev/dockerfiles-for-web-applications








Deploy Java application using containers 

1)Install Git & Clone the code & switch to that path

sudo yum install git -y
git clone <repo-url>
cd <repo-directory>
This pulls the source code (including pom.xml for Maven builds).

2) Install Maven & Build WAR

sudo yum install maven -y
mvn clean package
Output: Maven creates a target/ folder with: target/myweb-8.7.1.war

3) Install Docker

sudo yum install docker -y
sudo systemctl enable docker
sudo systemctl start docker
Verify: docker --version

4) Create a Dockerfile
Inside your project root (same directory where target/ is located):
Create a file named Dockerfile:

FROM tomcat:8.0.20-jre8
COPY target/*.war /usr/local/tomcat/webapps/flm.war


5) Build Docker Image

docker build -t image-name .

6) Run Container

docker run -itd --name=cont-name -p 8081:8080 image-name


7) Verify Deployment
List running containers:
docker ps


Docker file for NodeJS Application:



Multi Stage Dockerfile : Used to reduce the image size

Dockerfile:

FROM node:19-alpine AS firststage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 3000

FROM firststage AS finalstage
RUN npm install --production
COPY . .
CMD ["node", "index.js"]

<img width="722" height="23914" alt="image" src="https://github.com/user-attachments/assets/45f1ecd9-5159-43bb-ab99-334822001fb8" />
