(ns example.core
  (:require
   [om.core :as om :include-macros true]
   [om.dom :as dom :include-macros true]))

(enable-console-print!)



(def app-state (atom {:message "Hello World!"}))



;; -----
;; Bootstrap components
;; -----


(defn nav
  "Bootstrap nav header"
  [cursor owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
               (dom/nav #js{:id "header" :className "navbar navbar-default navbar-fixed navbar-sm"}
                        (dom/div #js{:className "container-fluid"}
                          (dom/div #js{:className "navbar-header"}
                                 (dom/a #js{:className "navbar-brand"}
                                        (dom/img #js {:src "/img/bn-logo.png" :height "25px"})))))))))




(defn app
  "Application root"
  [cursor owner]
  (reify om/IRender
    (render [_]
      (dom/div #js {:className ""}
               (om/build nav cursor)
               (dom/div #js{:className "container"}
                        (dom/div #js{:className "message"} (:message cursor)))))))


(defn main
  "Main"
  []
  (om/root
    app
    app-state
    {:target (. js/document (getElementById "app"))}))
