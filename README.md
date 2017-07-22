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

## Language Support

Currently, only English (US) is supported.

## Error Messages

This is a collection of error messages that you may encounter, and what they mean.

* `Connection is broken: "javax.net.ssl.SSLException: Unrecognized SSL message, plaintext connection?: 10.0.0.9:9092" [90067-193] 90067/90067`

    You will encounter this error message when attempting to connect via the browser console with a JDBC URL that specifies the protocol as SSL, but the TCP server is setup to not use SSL. To fix, either enable SSL on the TCP server, or change the JDBC URL in the browser console to use TCP as the protocol. (See [Issue #2](https://github.com/avojak/hydrogen/issues/2))

* `Connection is broken: "unexpected status 352518912" [90067-193] 90067/90067`

    You will encounter this error message when attempting to connect via the browser console with a JDBC URL that specifies the protocol as TCP, but the TCP server is setup to user SSL. To fix, either disable SSL on the TCP server, or change the JDBC URL in the browser console to use SSL as the protocol. (See [Issue #2](https://github.com/avojak/hydrogen/issues/2))