(defproject bloj "0.0.1"
  :description "bloj — a simple blog in Clojure"
  :url "https://github.com/O-I/bloj"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [postgresql "9.1-901.jdbc4"]
                 [hiccup "1.0.4"]
                 [com.cemerick/friend "0.2.1"]]
  :main ^:skip-aot bloj.web
  :uberjar-name "bloj-standalone.jar"
  :profiles {:uberjar {:aot :all}})