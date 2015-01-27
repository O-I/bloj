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

(def users {"admin" {:username "admin"
                    :password (creds/hash-bcrypt "password")
                    :roles #{::admin}}
            "rahul" {:username "rahul"
                    :password (creds/hash-bcrypt "password")
                    :roles #{::user}}})

(derive ::admin ::user)

(defroutes app-routes
  posts/routes
  (route/resources "/")
  (GET "/authorized" request
       (friend/authorize #{::user} "This page can only be seen by authenticated users."))
  (GET "/admin" request
       (friend/authorize #{::admin} "This page can only be seen by administrators."))
  (GET "/login" [] (-> "login.html"
                       (ring.util.response/file-response {:root "resources"})
                       (ring.util.response/content-type "text/html")))
  (friend/logout (ANY "/logout" request (ring.util.response/redirect "/")))
  (route/not-found (layout/four-oh-four)))

(def app
  (handler/site
    (friend/authenticate app-routes
        {:credential-fn (partial creds/bcrypt-credential-fn users)
                         :workflows [(workflows/interactive-form)]})))

(defn start [port]
  (ring/run-jetty app {:port port :join? false}))

(defn -main []
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))