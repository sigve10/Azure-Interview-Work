# Azure Interview Work (For SFE)
This application was developed as part of a challenge provided by SFE to develop a small full-stack application hosted on Azure.
## Prerequisites:
- Maven
- Java SDK 17
- Node
- NPM
## Setting up dev environment
In order to run the repository on your local machine:
- You must have a MSSQL database, either hosted or local
- You must define the following environment variables:
	- frontend-url=[The URL to your frontend, typically [http://localhost:3000/]()
	- database-password=[Your database password]
	- database-username=[Your database username]
	- database-url=jdbc:sqlserver://[your database URL]
- You must edit the variable `BACKEND_URL` in [/frontend/src/plugins/config.js]() to be the URL of your backend (typically [http://localhost:8080/]())

### Running the frontend
In order to run the frontend in dev mode, run the following commands:
```bat
cd frontend

npm ci

npm run dev
```
### Running the backend
In order to run the backend in dev mode, run the following commands:
```bat
cd backend

mvn clean install

mvn spring-boot:start
```
To stop the backend, run
```bat
mvn spring-boot:stop
```
# Demo site
The site is hosted at [https://lively-bush-0d3d36003.6.azurestaticapps.net/](). Whilst the front-end is continuously running, the backend is exceptionally on. All this means is that the favoriting feature will most likely not work on the hosted webpage unless agreed upon in advance. Aside from that, the site is fully functional.