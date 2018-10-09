(ns ynab.test
  (require [clojure.test :refer [deftest is]] 
           [ynab.main :as ynab]))

(deftest e2e
  (is (not (nil? ynab/access-token))))
(deftest base-url 
  (is (not (nil? ynab/base-url))))
