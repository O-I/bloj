(ns bloj.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :as ring]
            [bloj.controllers.posts :as posts]
            [bloj.views.layout :as layout]
            [bloj.models.migration :as schema]))

(defroutes app-routes
  posts/routes
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))

(def app
  (wrap-defaults app-routes site-defaults))

(defn start [port]
  ring/run-jetty app {:port port :join? false})

(defn -main []
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))