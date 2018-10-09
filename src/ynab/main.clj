(ns ynab.main
  (require [config.core :refer [env]]
           [clojure.pprint :refer [pprint]]
           [org.httpkit.client :as http]))


(def base-url (:base-url env))
(def access-token (:access-token env))
(pprint env)

(defn do-request 
  ([] (println "empty request"))
  ([endpoint]
   (http/get (apply str base-url "/" endpoint) {:headers {"Authorization" (str "Bearer: " access-token)}})))

(do-request "budgets")
