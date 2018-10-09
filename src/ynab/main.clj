(ns ynab.main
  (require [cprop.core :refer [load-config]]
           [clj-http.client :as http]
           [cheshire.core :refer [parse-string]]))

(def config (load-config))


(def base-url (:base-url config))
(def access-token (:access-token config))

(defn do-request 
  ([] (println "empty request"))
  ([endpoint]
   (let [url (apply str base-url "/" endpoint)
         options {:headers {"Authorization" (str "Bearer " access-token)}
                  :accept :json}]
     (http/get url options))))

(defn keywordize-body
  [resp]
  (parse-string (:body resp) true))


(defn get-endpoint-response-body
  [endpoint]
  (->>
    (do-request endpoint)
    (keywordize-body)))

(defn get-budgets
  []
  (get-endpoint-response-body "budgets"))
