# cljs-om-testing-example

An example repo demonstrating unit and browser-testing an Om application.

See the [blog post](http://lab.brightnorth.co.uk/2015/01/27/unit-and-browser-testing-om-clojurescript-applications) for more info.


## Usage

## Development
First do, from the command line, ```lein repl``` to get a REPL.

Then,
```clojure
(run)
```
and point a browser at http://localhost:10555

Now execute in the REPL:
```clojure
(browser-repl)
```


### Running the tests

```lein cljsbuild test``` to run the unit tests in PhantomJS, which must be installed on your system

```lein autotest``` to run the unit tests in autotest mode (where saving a file automatically reruns the tests)

```lein e2e``` to run just the end-to-end tests, in Chrome; requires [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/) on your system

```lein test``` to run all unit and e2e tests.
