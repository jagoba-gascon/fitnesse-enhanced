# fitnesse-json-contents
This responder prints the directory structure below the current folder in JSON format.

Developed and only tested in FitNesse version 20130530

```json
{"root": {
 "name": "XebiumTemplate",
 "childs": [
  {
   "name": "SuiteTearDown",
   "type": "Static"
  },
  {
   "name": "SuiteSetUp",
   "type": "Static"
  },
  {
   "name": "MySuite",
   "childs": [{
    "name": "MyFirstTest",
    "type": "Test"
   }],
   "type": "Suite"
  },
  {
   "name": "ScenarioLibrary",
   "type": "Static"
  }
 ],
 "type": "Static"
}}
```
