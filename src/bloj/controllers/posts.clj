(ns bloj.controllers.posts
  (:require
    [compojure.core :refer [defroutes GET POST]]
    [clojure.string :as str]
    [ring.util.response :as ring]
    [bloj.views.posts :as view]
    [bloj.models.post :as model]))

(defn index []
  (view/index (model/all)))

(defn create
  [post]
  (when-not (str/blank? post)
    (model/create post))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/" [] (index))
  (POST "/" [post] (create post)))