(ns ynab.main
  (require [clojure.string :refer [join]]
           [cprop.core :refer [load-config]]
           [clj-http.client :as http]
           [cheshire.core :refer [parse-string]]))

(def config (load-config))


(def base-url (:base-url config))
(def access-token (:access-token config))

(defn ^:private do-request 
  ([] (println "empty request"))
  ([endpoint]
   (let [url (str base-url "/" endpoint)
         options {:headers {"Authorization" (str "Bearer " access-token)}
                  :accept :json}]
     (http/get url options))))

(defn ^:private keywordize-body
  [resp]
  (parse-string (:body resp) true))


(defn ^:private get-endpoint-response-body
  [endpoint]
  (->>
    (do-request endpoint)
    (keywordize-body)))

(defn get-budgets
  []
  (get-endpoint-response-body "budgets"))

(defn get-budget-by-id
  [id]
  (get-endpoint-response-body (join "/" ["budgets" id] )))

(defn get-budget-settings
  [id]
  (get-endpoint-response-body (join "/" ["budgets" id "settings"])))
