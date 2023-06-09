# Github Repositories

This project is a RESTful API that retrieves non-fork repositories of a GitHub user and their branches.

## Getting Started

To get started with this project, you need to have the following prerequisites:

- Java Development Kit (JDK) installed
- Spring Boot framework
- Spring Web module


## Installation

1. Clone the repository to your local machine
```shell
git clone github.com/AnimusXov/github-repositories-api
```
2. Build the project using Maven:
```shell
mvn clean install
```
3. Run the application:

```shell
mvn spring-boot:run
```
4. The application will start running on http://localhost:8080.
# API Endpoints
## Retrieve User Repositories

Endpoint: **GET /repositories/{username}**

This endpoint retrieves the non-fork repositories of the specified GitHub user.

Request
```http
GET /repositories/{username}
Accept: application/json
```
Response
```json
[
  {
    "name": "repository1",
    "owner": {
      "login": "username"
    },
    "branches": [
      {
        "name": "branch1",
        "lastCommitSha": "commit_sha"
      },
      {
        "name": "branch2",
        "lastCommitSha": "commit_sha"
      }
    ]
  },
]
```
# Contributing

Contributions to this project are welcome! If you find any issues or would like to add new features, feel free to submit a pull request.

# License

This project is licensed under the MIT License. See the **LICENSE** file for more details.


Feel free to modify the content and structure of the README file according to your project's specific needs.
