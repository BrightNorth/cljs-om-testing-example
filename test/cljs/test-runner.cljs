(ns test-runner
  (:require
   [cljs.test :refer-macros [run-tests]]
   [example.test.core]))


(enable-console-print!)

(defn runner []
  (if (cljs.test/successful?
       (run-tests
        'example.test.core))
    0
    1))
