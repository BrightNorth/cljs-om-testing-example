(ns example.e2e
  (:use clojure.test)
  (:require [clj-webdriver.taxi :as t]
            [e2e-server]
            [environ.core :refer [env]]))


;; -----
;; Fixtures
;; -----

(defn selenium-fixture
  [& browsers]
  (fn [test]
    (doseq [browser browsers]
      (println (str "\n[ Testing " browser " ]"))
      (t/set-driver! {:browser browser})
      (test)
      (t/quit))))

(def svr (atom nil))

(defn svr-fixture
  []
  (fn [f]
    (swap! svr (fn [_] (e2e-server/run)))
    (f)
    (.stop @svr)))


(def is-dev? (env :is-dev))

(if is-dev?
  (use-fixtures :once (selenium-fixture :chrome))
  (use-fixtures :once (svr-fixture) (selenium-fixture :chrome)))




;; -----
;; Tests
;; -----


;; selectors

(def selector "div.message")


;; navigation

(defn go-to-homepage
  []
  (t/to "http://localhost:10555")) ;; TODO move to the config file


;; homepage

(deftest ^:browser homepage-has-title
  (go-to-homepage)
  (is (= "Hello World!" (t/text selector))))
