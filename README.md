# Hotel Manager

## Running the project

### Requirements

#### Java 8

Download and install the latest Java 8 version from [AdoptOpenJDK](https://adoptopenjdk.net/?variant=openjdk8).

#### Apache Maven

Download and install the latest version of [Maven](https://maven.apache.org/).

#### Apache Tomcat

Download and install Tomcat version 7.0.76 [here](https://archive.apache.org/dist/tomcat/tomcat-7/v7.0.76/).

#### Node.js & npm

Download and install the latest version of [Node.js and npm](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm).

### Backend

#### Tomcat

To run the backend of the project, run the following commands from ROOT in console:
- `mvn clean compile`
- `mvn install`

From `/apache-tomcat-7.0.76/bin/`
```bash
$ catalina.sh run
```

The application should be running in [http://localhost:8080/](http://localhost:8080).

#### Jetty

You may also run Jetty instead of Tomcat:
- `mvn clean compile`
- `mvn install`
- `cd webapp`
- `mvn jetty:run`
  - jetty runs by default on port 8080
  - if that's in use you can use instead:
    `mvn -Djetty.port=` `<anotherPort>` ` jetty:run`

Check `localhost:<portUsed>` to see if the application is running!

### Frontend

The frontend of the application was developed in ReactJS.

To run the development server, run the following commands from ROOT in console:
- `cd react-app/react-client`
- `npm run build`
- `npm start`

The application should be running in [http://localhost:3000](http://localhost:3000).

## Accounts Credentials

### Manager & Employees

Username: `manager`

Password: `password`




Username: `employee`

Password: `password`

### Hotel guests

Username: e-mail entered during reservation

Password: passwords are mailed to the reservation e-mail after performing log in.
