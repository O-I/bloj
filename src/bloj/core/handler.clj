(ns bloj.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :as ring]
            [bloj.controllers.posts :as posts]))

(defroutes app-routes
  (GET "/" [] (posts/index))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main []
  (ring/run-jetty #'app-routes {:port 8080 :join? false}))