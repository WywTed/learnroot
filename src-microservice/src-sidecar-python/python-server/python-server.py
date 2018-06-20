import json
from flask import Flask, Response
import requests
app = Flask(__name__)
@app.route("/health.json")
def health():
    result = {'status': 'UP'}
    return Response(json.dumps(result), mimetype='application/json')
@app.route("/getUser")
def getUser():
    result = {'username': 'python', 'password': 'python'}
    return Response(json.dumps(result), mimetype='application/json')
    
@app.route("/java-user")
def javaUser():
	res = requests.get("http://localhost:5678/sidecar-java/java-user")
	return Response(json.dumps(res.text), mimetype='application/json')
app.run(port=3000, host='0.0.0.0')