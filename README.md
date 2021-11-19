# Crab color prediction #
This is a java wrapper around a weka model. This project can take the gender and five morphological measurements of a Leptograpsus variegatus and predict the color of this crab. It can also take an input file with measurements for multiple crabs. It has an 88% accuracy.

## Table of content

- [Project description](#project-description)
- [Installation](#installation)
    * [Needed](#needed)
    * [Dependencies](#dependencies)
- [Input file specifications](#Input-file-specifications)
- [Arguments](#arguments)
- [Contact](#contact)

## Project description
This project uses a J48 model. This model predicts the color of the crab, based on the gender and morphological measurements. Out of the test for what machine learning algorithm could predict this the best, it was SimpleLogistic that reached the highest score. But since I have been struggling with things outside this project, I was unable to get a SimpleLogistic model to work.

This application can be followed and cloned by going to [git repo](https://github.com/IJsbarnd/JavaWrapperGit2)
I have had many struggles with GitHub for this project, so there are very few commits. This is because I had to create multiple new repositories since I couldn't get the others to work. Also, since I didn't know using a GitHub repository was obligated (I thought it was recommended), I started working on this project in other ways. 

## Installation
To install this program, the repository can be downloaded or cloned. The program can then run on your system, provided the current version of java is up-to-date.

``` 
git pull https://github.com/IJsbarnd/JavaWrapperGit2
git clone https://github.com/IJsbarnd/JavaWrapperGit2
```

After pulling or cloning the git you can build the project by making use of the given gradle.build, this will result in a jar file which can be executed

```
java -jar .\build\libs\Wrapper-1.0-SNAPSHOT-all.jar [options]
```

### Needed
* Java version 16 SDK
* Gradle

### Dependencies
|Group ID                               |Artifact ID                            |Version            |   
|---                                    |---                                    |---                |
|commons-cli                            |commons-cli                            |1.4                |
|nz.ac.waikato.cms.weka                 |weka-stable                            |3.8.0              |
|commons-io                             |commons-io                             |2.6                |

## Arguments
The program can take 4 arguments.

|Short                                  |Long                                   |Description                                 |
|---                                    |---                                    |---                                         |
|-h                                     |Help                                   |Returns the help information                |
|-f                                     |Input file                             |The inputfile to be classified              |
|-i                                     |Instance                               |A string of an instance to be classified    |
|-a                                     |Order                                  |the order of the attributes                 |


## Input file specifications
If the input is an instance, it should be used as following:
```
-i "[gender],[FL],[RW],[CL],[CW],[BD],?"
```
The gender attribute only accepts a "M" for male or a "F" for female, and all measurements are in millimeters. The last attribute, the species attribute that will be predicted, is substituted with a "?". Only these values are allowed, otherwise the program will fail.

If the input is a file, it has to be a ``.arff`` file. The order of the attributes in this file is very important. The instances in the file should be comma separated values, like the instance input.

## Command examples
These are some examples for commmands to run this program. These commands can only be used after creating a ShadowJar with the gradle.build.

This command will classify 8 instances from the test .arff file.
```
java -jar build/libs/Wrapper-1.0-SNAPSHOT-all.jar -f src/main/data/testfile.arff
```

This command will classify the instance from the commandline.
```
java -jar build/libs/Wrapper-1.0-SNAPSHOT-all.jar -i "M,8.1,6.7,16.1,19,7,?"

```


## Contact

* i.j.a.pool@st.hanze.nl