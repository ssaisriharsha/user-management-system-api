<h1>User Management System (REST API)</h1>
<hr/>
<h2>Introduction</h2>
<p>A full fledged CRUD REST API to manage details of user.</p>
<hr/>
<h2>Technologies used</h2>
<ol>
    <li><b>Java</b></li>
    <li><b>Spring Boot</b></li>
    <li><b>Hibernate/JPA</b></li>
    <li><b>PostgreSQL</b></li>
</ol>
<hr/>
<h2>API Documentation</h2>
<p>This api consists of the following Endpoints. All the requests should be made to <code>https://{baseAddress}/api/</code></p>
<ul>
    <li><code>users</code></li>
    <li><code>users/{id}</code></li>
</ul>

<p>The following operations are permitted on <code>users</code>:</p>
<ul>
    <li>
        <p>
            <code>GET</code>:
            This operation performs get request on users and retrieves the list of users from the database. Following are the possible responses that you can expect from the api.
            <ul>
                <li>
                    Status: <code>200 OK</code>.
                </li>
            </ul>
        </p>
    </li>
    <li>
        <p>
            <code>POST</code>:
            This operation adds the given user to the database. Following are the possible responses that you can expect when dealing with this request.
            <ul>
                <li>
                    Status: <code>201 CREATED</code>, if resource is created successfully.
                </li>
                <li>
                    Status: <code>409 CONFLICT</code>, if the given resource already exists but requested to duplicate again.
                </li>
            </ul>
        </p>
    </li>
</ul>
<br/>

<p>The following operations are permitted on <code>users/{id}</code>:</p>
<ul>
    <li>
        <p>
            <code>GET</code>:
            This operation performs get request on user with specified ID and retrieves the user, if exists, from the database. Following are the possible responses that you can expect from the api.
            <ul>
                <li>
                    Status: <code>302 FOUND</code>, if the user with specified ID exists.
                </li>
                <li>
                    Status: <code>404 NOT FOUND</code>, if the required user doesn't exists in the database.
                </li>
            </ul>
        </p>
    </li>
    <li>
        <p>
            <code>PUT</code>:
            This operation updates the user with given ID. Following are the possible responses that you can expect when dealing with this request.
            <ul>
                <li>
                    Status: <code>202 ACCEPTED</code>, if resource is updated successfully.
                </li>
                <li>
                    Status: <code>404 NOT FOUND</code>, if the resource with given ID doesn't exists in the database.
                </li>
                <li>
                    Status: <code>403 FORBIDDEN</code>, if request is made to create an user using this endpoint.
                </li>
            </ul>
        </p>
    </li>
    <li>
        <p>
        <code>DELETE</code>:
        This operation deletes the user with specified ID from the database. The following are the responses that you can expect while dealing with this request.
            <ul>
                <li>
                    Status: <code>200 OK</code>, if the requested user is deleted successfully.
                </li>
                <li>
                    Status: <code>404 NOT FOUND</code>, if the user with specified ID is not found in the database.
                </li>
            </ul>
        </p>
    </li>
</ul>
<hr/>

## Getting Started

### Prerequisites
- **Java 11+**
- **Maven/Gradle**
- **PostgreSQL**

### Installation and Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/user-management-system.git
   cd user-management-system
 2. Setup the database with users table.
3. Add the following properties to your application.properties
    ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/<your-database>
    spring.datasource.username=<your-username>
    spring.datasource.password=<your-password>
4. Now you can run the spring boot application using
   ```bash
   mvn spring-boot:run
Note: Alternatively, you can use the packaged jar file to run program. :)

<hr/>
<h2>License</h2>
<p>This application is licensed under GPL V3 license.</p>

<hr/>
<h2>Contact</h2>

Email: [saisri2946@gmail.com](mailto:saisri2946@gmail.com)