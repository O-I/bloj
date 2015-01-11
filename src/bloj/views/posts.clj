(ns bloj.views.posts
  (:require [bloj.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]))

(defn post-form []
  [:div {:id "post-form" :class "sixteen columns alpha omega"}
   (form/form-to [:post "/"]
                 (form/label "post" "What do you want to post?")
                 (form/text-area "post")
                 (form/submit-button "post!"))])

(defn display-posts [posts]
  [:div {:class "posts sixteen columns alpha omega"}
   (map
    (fn [post] [:h2 {:class "post"} (h (:body post))])
    posts)])

(defn index [posts]
  (layout/common "bloj"
                 (post-form)
                 [:div {:class "clear"}]
                 (display-posts posts)))