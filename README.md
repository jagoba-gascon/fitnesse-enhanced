# fitnesse-json-contents
This responder prints the directory structure (with name and page type) below the current folder in JSON format.

Developed and only tested in FitNesse version 20130530

```json
{
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
}
```

# Instalation
* Download project
* Build project
```
 mvn install
```
* Update your pom
```xml
    <dependency>
      <groupId>json-contents</groupId>
      <artifactId>json-contents</artifactId>
      <version>1.0.0</version>
      <scope>runtime</scope>
    </dependency>
```
* Update your plugins.properties
```
...
Responders=jsonContents:com.jago.fitnesse.jsonContents.SuiteResponder
```
# Usage
```
http://localhost:8000/XebiumTemplate?jsonContents
```
