(ns bloj.views.friend
  (:require [bloj.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]))

(defn login-form []
  [:div {:class "row"}
    [:div {:class "columns small-12"}
      [:h3 "Login"]
      [:div {:class "row"}
        [:form {:method "POST" :action "login" :class "columns small-4"}
          [:div "Username" [:input {:type "text" :name "username"}]]
          [:div "Password" [:input {:type "password" :name "password"}]]
          [:div [:input {:type "submit" :class "button" :value "Login"}]]]]]])

(defn login []
  (layout/common "bloj"
    (login-form)))