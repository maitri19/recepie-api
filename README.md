Recipe API 
-----
## Running the application

Clone the repository to your local 

```
git clone https://github.com/maitri19/recepie-api.git

```

Go to root directory of the code. Run following command to run the application. 

```
mvn clean install

```

Now the application is build to run. Use following command to run spring boot application on command prompt.

```
mvn spring-boot:run

```

In case, If you want to run the application directly from IDE. Import the project to your favorite IDE as 'Existing maven project'. Select project from the IDE and run it as java application. “Don't forget to update maven ;)” I have configured the port to 8882 in properties. So the application can be accessed by `http://localhost:8882`

Application is using H2 inmemory database. Following is the details of connection. 
- `http://localhost:8882`
- spring.datasource.url=jdbc:h2:file:./data/demo
- spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
- spring.datasource.driverClassName = org.h2.Driver
- spring.datasource.platform=h2
- spring.datasource.username = sa
- spring.datasource.password =

Swagger is integrated for easy access of API. It can be accessed via `http://localhost:8882/swagger-ui.html`

## Authorization Details

Basic authentication is implemented. You can access the api on the through following user details. This is only for demo purpose. I had to make CSRF disabled for testing purpose. It is not supposed to be used in production environment. 

- username: user1
- password: user1Pass

## API details

- Create Recipe
	- Create recipe service is used to create new recipe. Following is the api url and sample json. It is POST request which accepts JSON body for recipe.

```
		POST 
		'Content-Type: application/json'
		'/api/v1/recipe/'
		
		'Accept: application/json' -d 
		'{ \ 
		   "cookingInstruction": "string", \ 
		   "ingredients": [ \ 
		     { \ 
		       "ingredientDesciption": "string", \ 
		       "ingredientName": "string" \ 
		     } \ 
		   ], \ 
		   "name": "string", \ 
		   "numberOfPerson": 0, \ 
		   "vegetarian": true \ 
		}' 
		
	
```
- Update Recipe
	- Update recipe service is used to update existing recipe. Following is the api url and sample json. It is PUT request which accept JSON body for recipe alongwith ID as path parameter. 
	
```
		PUT 
		'Content-Type: application/json' 
		'/api/v1/recipe/{id}'
		
		'Accept: application/json' -d 
		'{ \ 
		   "id": 5, \ 
		   "name": "string test", \ 
		   "createDate": "2020-10-26 13:26", \ 
		   "numberOfPerson": 0, \ 
		   "cookingInstruction": "string", \ 
		   "ingredients": [ \ 
		     { \ 
		       "id": 10, \ 
		       "ingredientName": "string", \ 
		       "ingredientDesciption": "string" \ 
		     } \ 
		   ], \ 
		   "vegetarian": true \ 
		}'	 
		

```
 
- Get All Recipes
	- Get all recipe service is used to get list of all the recipes. Following is the api url. It is GET request. 

```
		GET 
		'Accept: application/json' 
		'/api/v1/recipe/'

```

- Find Recipe By Id
	- Find the recipe by id can be used to fetch particular Recipe. Following is the api url. It is GET request which accept ID as path parameter.
	
```	
		GET 
		'Accept: application/json' 
		'/api/v1/recipe/{id}'

```
	
- Delete Recipe By Id
	- Delete the recipe by id can be used to delete particular Recipe. Following is the api url. It is DELETE request which accept ID as path parameter.
	
```	
		GET 
		'Accept: application/json' 
		'/api/v1/recipe/{id}'

```
		
## Test Scripts

Postman test collection is provided in postman/ folder.
