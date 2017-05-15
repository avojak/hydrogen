# Hydrogen

[![Build Status](https://travis-ci.org/avojak/hydrogen.svg?branch=master)](https://travis-ci.org/avojak/hydrogen) [![Coverage Status](https://coveralls.io/repos/github/avojak/hydrogen/badge.svg?branch=master)](https://coveralls.io/github/avojak/hydrogen?branch=master) [![License](https://img.shields.io/badge/license-EPL%201.0-blue.svg)](https://opensource.org/licenses/EPL-1.0) ![Version](https://img.shields.io/badge/version-1.0.0--SNAPSHOT-yellow.svg)

Hydrogen is an Eclipse plugin to configure and launch one or more [H2](http://www.h2database.com/html/main.html) database servers. Server instances can be launched directly from the Eclipse Run Configuration menu.

## Getting Started

These instructions will get you setup to use Hydrogen in your local Eclipse installation.

### Prerequisites

 - TODO: Eclipse minimum requirements
 - TODO: JRE minimum requirements
 - The H2 JAR is not packaged with Hydrogen and must be [downloaded](http://www.h2database.com/html/download.html) separately

### Installing

Hydrogen is currently only available as an archived Eclipse update site. 

 - [1.0.0-SNAPSHOT](#)

End with an example of getting some data out of the system or using it for a little demo

### Configuration

Configuring Hydrogen

## Building

To build Hydrogen in a development environment, simply use the following Maven command:

```
man clean package
```

An archived Eclipse update site will be generated in the `hydrogen.site` module:

```
hydrogen/hydrogen.site/target/hydrogen.site-${version}.zip
```

## Running the tests

The [JUnit](http://junit.org/junit4/) tests can be run via the following Maven command:

```
mvn clean verify [-Pjacoco]
```

The JaCoCo profile will generate code coverage information if used.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Travis CI](https://travis-ci.org) - Continuous Integration and Deployment
* [JaCoCo](http://www.eclemma.org/jacoco/) - Code Coverage

## Versioning

I use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## License

This project is licensed under the Eclipse Public License v1.0 - see the [LICENSE.md](LICENSE.md) file for details.