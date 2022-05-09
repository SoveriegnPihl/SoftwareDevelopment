Software 1 02161

Her er vores version af kursets afsluttende projekt Lavet af Marcus, Victor, Victor, Jakob og Thomas

Programmet er et internt system med en grafisk brugerflade, som indeholder funktioner som tidsstyring, projekt- og aktivitetshåndtering samt en database, der gemmer foretagede ændringer. Programmet køres fra Main i GUI-pakken og al logik er fra projektpakken, her kan du bruge initialerne fra udviklerdatabasen til at logge ind, enten som projektleder eller almindelig udvikler. Det er så muligt at se al funktionalitet og lege med den! God fornøjelse

Test guide: (IntelliJ IDEA) skrevet af Victor
src/test skal være valgt som test root. Derefter kan der køres test på flere forskellige måder. For at køre alle cucumber
test så kan du enten køre klassen AcceptanceTest eller højreklikke på pakken src/test/dtu/stepDefinitions og trykke "Run
test in 'dtu.stepDefinitions'". Hvis der skal køres med code coverage, højreklikkes der på samme pakke, derefter "Run more"
hvor der vælges Run Tests in 'dtu.stepDefinitions' with code coverage. Der skal muligvis ændres i configuration, så
det er pakken src/src/dtu/project bliver valgt. De kan også køres individuelt ved at køre dem fra feature filerne.Der bruges 
samme fremgangsmåde med whitebox test der ligger i mappen src/test/dtu/project. De kan også både køres individuelt eller alle på samme tid. 

Det er vigtigt, at csv-filerne har følgende indhold når testene skal køres.

activities.csv:
22002,Lave kage,2022,0,25,2022,0,27,5,2.0,100
22002,Prepare project,2022,0,27,2022,0,29,5,127.0,300
22001,Distribute tasks,2022,0,2,2022,0,10,3,0.0,100
22001,Collect logs,2022,0,2,2022,0,5,3,105.0,200
22001,Create tasks,2022,0,2,2022,0,10,6,0.0,100
22001,Test solutions,2022,0,2,2022,0,10,9,725.0,100
22002,Spille bold,2022,0,22,2022,0,25,3,0.0,100

developers.csv:
vic7,noOcc,noSick
vicc,noOcc,2022,4,9,2022,4,10
asdg,noOcc,noSick
tom1,noOcc,noSick
jako,noOcc,noSick
ekki,noOcc,noSick
lmao,noOcc,noSick

projects.csv:
22001,2022,0,1,2022,0,14,700
22002,2022,0,21,2022,1,10,800
22003,2022,2,14,2022,2,21,900
22004,2022,0,5,2022,1,7,5000
22005,2022,0,5,2022,0,4,5
22006,2022,0,5,2022,0,4,500
22007,2022,0,5,2022,0,14,600
22008,2022,0,1,2022,1,1,100
22009,2022,0,6,2022,0,7,10

