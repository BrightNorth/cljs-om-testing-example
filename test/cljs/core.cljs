(ns example.test.core
  (:require-macros [cemerick.cljs.test :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test]
            [dommy.core :refer-macros [sel sel1]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(deftest dummy
  (is (= 1 1)))
