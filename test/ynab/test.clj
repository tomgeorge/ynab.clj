(ns ynab.test
  (require [clojure.test :refer [deftest is]] 
           [ynab.main :as ynab]))

(deftest config-access-token
  (is (not (nil? ynab/access-token))))
(deftest config-base-url
  (is (not (nil? ynab/base-url))))
(deftest e2e-response-is-ok
  (is (= 200 (:status (ynab/do-request "budgets")))))
(deftest response-body-is-map
  (is (map? (ynab/keywordize-body (ynab/do-request "budgets")))))

