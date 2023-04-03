from fastapi import FastAPI
from pydantic import BaseModel
import json
import os

app = FastAPI()


class Data(BaseModel):
    id: int
    name: str

@app.post("/test")
async def root(data : Data):

    test_dict = dict()
    test_dict["id"] = data.id
    test_dict["name"] = data.name
    test_dict["email"] = "test@test.com"

    test_json = json.dumps(test_dict)

    print(data.id)
    print(data.name)
    print(test_dict)
    print(test_json)
    print(type(data))
    
    return test_dict