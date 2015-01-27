(defproject example "0.1.0-SNAPSHOT"
  :description "Example app demonstrating unit testing and integration testing techniques."
  :url "http://github.com/BrightNorth/cljs-om-testing-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371" :scope "provided"]
                 [ring "1.3.1"]
                 [compojure "1.2.0"]
                 [enlive "1.1.5"]
                 [om "0.7.3"]
                 [prismatic/dommy "1.0.0"]
                 [figwheel "0.1.4-SNAPSHOT"]
                 [clj-webdriver "0.6.1"]
                 [environ "1.0.0"]
                 [riddley "0.1.7"]
                 [com.cemerick/piggieback "0.1.3"]
                 [weasel "0.4.0-SNAPSHOT"]
                 [leiningen "2.5.0"]]

  :plugins [[lein-cljsbuild "1.0.4"]
            [com.cemerick/clojurescript.test "0.3.3"]
            [lein-environ "1.0.0"]]

  :min-lein-version "2.5.0"

  :main example.server

  :uberjar-name "example.jar"

  :clean-targets ^{:protect false} ["resources/public/js/app.js" "resources/public/js/out.js.map" "resources/public/js/out" "target"]

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:output-to     "resources/public/js/app.js"
                                        :output-dir    "resources/public/js/out"
                                        :source-map    "resources/public/js/out.js.map"
                                        :preamble      ["react/react.min.js"]
                                        :externs       ["react/externs/react.js"]
                                        :optimizations :none
                                        :pretty-print  true}}}}

  :aliases {"e2e" ["do" "clean" ["with-profiles" "-dev,+e2e" ["cljsbuild" "once"]] ["with-profiles" "-dev,+e2e" "test"]]
            "autotest" ["do" "clean" ["cljsbuild" "auto" "test"]]
            "test" ["do" "clean" ["cljsbuild" "test"] "clean" ["with-profiles" "-dev,+e2e" ["cljsbuild" "once"]] ["with-profiles" "-dev,+e2e" "test"]]}

  :source-paths ["src/clj"]

  :profiles {:dev {:repl-options {:init-ns example.server
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :plugins [[lein-figwheel "0.1.4-SNAPSHOT"]]

                   :figwheel {:http-server-root "public"
                              :port 3449
                              :css-dirs ["resources/public/css"]}

                   :env {:is-dev true}

                   :cljsbuild {
                               :test-commands {"test" ["phantomjs" :runner "phantom/bind-polyfill.js" "target/cljs/testable.js"]}
                               :builds {
                                        :app {:source-paths ["src/cljs" "env/dev/cljs"]}
                                        :test {:source-paths ["src/cljs" "test/cljs"]
                                               :notify-command ["phantomjs" :cljs.test/runner "phantom/bind-polyfill.js" "target/cljs/testable.js"]
                                               :compiler {:output-to "target/cljs/testable.js"
                                                          :optimizations :whitespace
                                                          :preamble ["react/react.min.js"]
                                                          :pretty-print true}}
                                        }
                               }
                   }

             :e2e {
                   :source-paths ["e2e/clj" "test/clj"]
                   :aot :all
                   :cljsbuild {:builds {:app
                                        {:source-paths ["env/prod/cljs" "src/cljs"]
                                         :compiler
                                         {:optimizations :simple
                                          :pretty-print false}}}}}

             :uberjar {:hooks [leiningen.cljsbuild]
                       :env {:production true}
                       :omit-source true
                       :aot :all
                       :source-paths ["src/clj" "src/cljs"]
                       :cljsbuild {:builds {:app
                                            {:source-paths ["env/prod/cljs"]
                                             :compiler
                                             {:optimizations :advanced
                                              :pretty-print false}}}}}})
