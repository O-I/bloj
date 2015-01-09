(ns bloj.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [bloj.controllers.posts :as posts-controller]))

(defroutes app-routes
  (GET "/" [] (posts-controller/index))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
