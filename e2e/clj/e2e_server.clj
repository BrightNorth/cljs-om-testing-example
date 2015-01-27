(ns e2e-server
  (:require [clojure.java.io :as io]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [compojure.handler :refer [api]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]))


(defroutes routes
  (resources "/")
  (resources "/react" {:root "react"})
  (GET "/*" req (io/resource "index.html")))

(def http-handler
  (api routes))

(defn run [& [port]]
  (defonce ^:private server
    (let [port 10555]
      (print "Starting E2E web server on port" 10555 ".\n")
      (run-jetty http-handler {:port port
                          :join? false})))
  server)

(defn -main [& [port]]
  (run port))
