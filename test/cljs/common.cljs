(ns example.test.common
  (:require-macros [cemerick.cljs.test :refer (is deftest with-test run-tests testing)])
  (:require [cemerick.cljs.test :as t]
            [dommy.core :refer-macros [sel sel1]]
            [om.core :as om :include-macros true]))

(defn insert-container! [container]
  (dommy.core/append! (sel1 js/document :body) container))


(defn new-container! []
  (let [id (str "container-" (gensym))
        n (.createElement js/document "DIV")]
    (set! (.-id n) id)
    (insert-container! n)
    (sel1 (str "#" id))))
