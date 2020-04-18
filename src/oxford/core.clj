(ns oxford.core
  (:require [clj-http.client :as http]
            [clojure.data.json :as json]))


(def !application-id
  (atom nil))


(def !application-key
  (atom nil))


(def !language
  (atom nil))


(defmacro assert!
  [expr message]
  `(let [x# ~expr]
     (or x#
         (throw (AssertionError. (str "Assert failed: " ~message "\nIn: " (pr-str '~expr)))))))


(defn init!
  [& {:keys [application-id application-key language]}]
  (assert! (string? application-id) "application-id must be a string")
  (assert! (string? application-key) "application-key must be a string")
  (assert! (or (nil? language) (string? language)) "language must be a string")
  (reset! !application-id application-id)
  (reset! !application-key application-key)
  (reset! !language language))


(defn request
  [word & {:keys [language]
           :or   {language @!language}}]
  (let [app-id (assert! @!application-id "no app-id set")
        app-key (assert! @!application-key "no app-key set")
        _ (assert! (string? language) "no language specified")
        url (format "https://od-api.oxforddictionaries.com:443/api/v2/entries/%s/%s"
                    language word)
        headers {:headers {"app_id"  app-id
                           "app_key" app-key}}]
    (-> (http/get url headers)
        (:body)
        (json/read-str :key-fn keyword))))


(defn define
  [word & {:keys [language]
           :or   {language @!language}
           :as   args}]
  (some->> (apply request word args)
           (:results)
           (first)
           (:lexicalEntries)
           (first)
           (:entries)
           (first)
           (:senses)
           (map (comp first :definitions))))
