(ns bloj.web
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as ring]
            [bloj.controllers.posts :as posts]
            [bloj.views.layout :as layout]
            [bloj.models.migration :as schema]
            [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds]))
  (:gen-class))
  

(defroutes app-routes
  posts/routes
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))

(def app
  (handler/site app-routes))

(defn start [port]
  (ring/run-jetty app {:port port :join? false}))

(defn -main []
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))