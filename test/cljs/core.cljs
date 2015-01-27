(ns example.test.core
  (:require-macros [cljs.test :refer (is deftest testing)])
  (:require [cljs.test]
            [dommy.core :refer-macros [sel sel1]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(deftest dummyPasses
  (is (= 1 1)))
