# oxford

A Clojure wrapper around the [Oxford Dictionaries API](https://developer.oxforddictionaries.com/).

## Usage

After registering with the Oxford Dictionaries API, initialise the library with your application ID and application key:

```clojure
(require '[oxford.core :as ox])

(ox/init!
  :application-id "your id here"
  :application-key "your key here"
  :language "en-gb")
```

If you initialise the library with a language, subsequent requests will be looked up in that language. Otherwise you have to specify the language when you make your request.

To make a request about a specific word:

```clojure
(ox/request "word")
```

You get back the JSON response as Clojure data:

```clojure
{:id "cattle",
 :metadata {:operation "retrieve", :provider "Oxford University Press", :schema "RetrieveEntry"},
 :results [{:id "cattle",
            :language "en-gb",
            :lexicalEntries [{:derivatives [{:id "cattle-like", :text "cattle-like"}],
                              :entries [{:etymologies ["Middle English (also denoting personal property or wealth): from Anglo-Norman French catel, variant of Old French chatel (see chattel)"],
                                         :grammaticalFeatures [{:id "plural", :text "Plural", :type "Number"}],
                                         :senses [{:definitions ["large ruminant animals with horns and cloven hoofs, domesticated for meat or milk, or as beasts of burden; cows and oxen."],
                                                   :domains [{:id "farming", :text "Farming"}],
                                                   :id "m_en_gbus0160260.006",
                                                   :notes [{:text "Bos taurus (including the zebu, B. indicus), family Bovidae; descended from the extinct aurochs",
                                                            :type "technicalNote"}],
                                                   :shortDefinitions ["large ruminant animals with horns and cloven hoofs, domesticated for meat or milk"],
                                                   :synonyms [{:language "en", :text "cows"}
                                                              {:language "en", :text "bovines"}
                                                              {:language "en", :text "oxen"}
                                                              {:language "en", :text "bulls"}],
                                                   :thesaurusLinks [{:entry_id "cattle", :sense_id "t_en_gb0002122.001"}]}
                                                  {:definitions ["animals of a group related to domestic cattle, including yak, bison, and buffaloes."],
                                                   :domains [{:id "mammal", :text "Mammal"}],
                                                   :id "m_en_gbus0160260.010",
                                                   :notes [{:text "Tribe Bovini, family Bovidae (the cattle family): four genera, in particular Bos. The cattle family also includes the sheep, goats, goat-antelopes, and antelopes",
                                                            :type "technicalNote"}],
                                                   :shortDefinitions ["animals of group related to domestic cattle"]}]}],
                              :language "en-gb",
                              :lexicalCategory {:id "noun", :text "Noun"},
                              :pronunciations [{:audioFile "http://audio.oxforddictionaries.com/en/mp3/cattle_gb_1.mp3",
                                                :dialects ["British English"],
                                                :phoneticNotation "IPA",
                                                :phoneticSpelling "ˈkat(ə)l"}],
                              :text "cattle"}],
            :type "headword",
            :word "cattle"}],
 :word "cattle"}
```

Or to get the definitions of a word:

```clojure
(ox/define "cattle")
```

```clojure
("large ruminant animals with horns and cloven hoofs, domesticated for meat or milk, or as beasts of burden; cows and oxen."
 "animals of a group related to domestic cattle, including yak, bison, and buffaloes.")
```
