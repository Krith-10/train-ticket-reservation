# All the testing were performed in POSTMAN
# This is a basic python client that performs CRUD HTTP requests on the backend server
# Assumption is all this operation would be based on a single user

import requests
import json

# POST Request - Create a reservation
url = "http://localhost:8080/v1/reservation"
payload = json.dumps({
  "from": "London",
  "to": "France",
  "price": 50,
  "user": {
    "firstName": "Krithick",
    "lastName": "S",
    "email": "kri@gmail"
  }
})
headers = {
  'Content-Type': 'application/json'
}
response = requests.request("POST", url, headers=headers, data=payload)
print(response.text)
response_data = response.json()
user_data = response_data['user']
user_id = str(user_data['id'])
print(user_id)

# Get Request - Get the reservation of the user
url = "http://localhost:8080/v1/reservation/"+user_id
payload = {}
headers = {}
response = requests.request("GET", url, headers=headers, data=payload)
print(response.text)

# Patch Request - Modify the seating of the user
import requests
url = "http://localhost:8080/v1/reservation/"+user_id+"/seats/modify"
payload = {}
headers = {}
response = requests.request("PATCH", url, headers=headers, data=payload)
print(response.text)

# Get Request - Section (A) Details 
import requests
url = "http://localhost:8080/v1/section/A/details"
payload = {}
headers = {}
response = requests.request("GET", url, headers=headers, data=payload)
print(response.text)

# DELETE Request - Delete the reservation by user
import requests
url = "http://localhost:8080/v1/reservation/"+user_id
payload = {}
headers = {}
response = requests.request("DELETE", url, headers=headers, data=payload)
print(response.text)
