# StudentAttendence

To make docker container with volume in project folder run:
##### NB! You might have to change - and "" for the command to work

```{r, engine='bash', count_lines}
docker run --rm -v “[YOUR_PATH_TO_PROJECT_FOLDER]/StudentAttendence/data:/h2-data" --name h2-studentattendance -d -p 9092:9092 -p 8082:8082 buildo/h2database 
```

Go to localhost:8082 and login to database:
 
<img width="432" alt="Skærmbillede 2021-10-28 kl  13 55 36" src="https://user-images.githubusercontent.com/44894156/139255217-7d1dd14a-103a-45f0-867d-95a345c5761d.png">
