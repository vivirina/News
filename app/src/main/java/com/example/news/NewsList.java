package com.example.news;

import android.util.Log;

import com.example.news.api.Request;
import com.example.news.api.Response;
import com.example.news.api.Server;
import com.example.news.api.ServerMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsList {

    private static HashMap<Integer, NewsList> instances = new HashMap<>();

    public static NewsList getInstance(Category category){
        synchronized (NewsList.class) {
            NewsList existingList = instances.get(category.getId());
            if (existingList != null) {
                return existingList;
            } else {
                existingList = new NewsList(category);
                instances.put(category.getId(), existingList);
                return existingList;
            }
        }
    }

    private Category category;
    private List<News> newsList = new ArrayList<>();

    private NewsList(Category category){
        this.category = category;
        /*
        News news;
        news= new News(1, "В Италии стартует первый международный фестиваль, объединяющий русскоязычные театры","Есть на культурной карте совершенно удивительное явление, существующее как будто в иной реальности — это русские театры за рубежом, иногда двуязычные, иногда моноязычные, но как бы то ни было — русские, сохраняющие свой особый стиль, вкус и статус. Так вот театральная премия зрительских симпатий «Звезда Театрала» совместно с Союзом театральных деятелей России затеяли необычный, но важный проект — Первый международный фестиваль «Мир русского театра», который вот-вот начнется в Италии, в городке Монтекатини Терме, что в Тоскане.",CategoriesList.getInstance().getCategory(1));
        CategoriesList.getInstance().getCategory(1).addNews(news);
        newsList.add(news);
        news = new News(2, "Писательница Алиса Даншох зовет с собой в путешествие по Флоренции", "Творчество Алисы Даншох весьма разнопланово: после ставших популярными «Кулинарных воспоминаний счастливого детства» и истории «Не совсем святого семейства из Серебряного переулка» в свет вышла уже пятая книга, в основу которой легли культурологические эссе о Флоренции — городе, который стал ярким эпизодом, или даже целой главой, для многих представителей отечественной культуры — от Фонвизина и Достоевского до Бродского и Андрея Тарковского. Посмотрим же на Флоренцию глазами Алисы Даншох.",CategoriesList.getInstance().getCategory(1));
        CategoriesList.getInstance().getCategory(1).addNews(news);
        newsList.add(news);
        news = new News(3, "Сборная России начала подготовку к Кубку Конфедераций-2017","С момента объявления Станиславом Черчесовым  расширенного состава на Кубок конфедераций сборная уже успела понести три ощутимые потери. Из-за травм национальная команда не досчиталась Андрея Лунева, Алана Дзагоева и Марио Фернандеса. Тренерский штаб нашел замену только третьему вратарю – в срочном порядке был приглашен Владимир Габулов, который не примерял майку сборной более четырех лет.",CategoriesList.getInstance().getCategory(2));
        CategoriesList.getInstance().getCategory(2).addNews(news);
        newsList.add(news);
        news = new News(4, "Итальянские мастера. В чем секрет Карреры и других тренеров-чемпионов?", "Нельзя говорить об итальянской тренерской школе, не упомянув Коверчано – район Флоренции, где уже на протяжении многих десятилетий проходят подготовку все, кто мечтает посвятить себя этому непростому ремеслу. Марчелло Липпи, Арриго Сакки, Антонио Конте, Клаудио Раньери, Роберто Манчини, Карло Анчелотти, Массимо Каррера  и многие другие специалисты, которых подарила нам Серия A, познавали азы мастерства управления командой именно здесь. За долгие годы местная школа копила знания, традиции, обменивалась опытом с зарубежными коллегами и оттачивала футбольную науку, чтобы на выходе получить самый сильный тренерский корпус в мире.",CategoriesList.getInstance().getCategory(2));
        CategoriesList.getInstance().getCategory(2).addNews(news);
        newsList.add(news);
        */
        /*
        newsList.add(new News(1, "В Италии стартует первый международный фестиваль, объединяющий русскоязычные театры","Есть на культурной карте совершенно удивительное явление, существующее как будто в иной реальности — это русские театры за рубежом, иногда двуязычные, иногда моноязычные, но как бы то ни было — русские, сохраняющие свой особый стиль, вкус и статус. Так вот театральная премия зрительских симпатий «Звезда Театрала» совместно с Союзом театральных деятелей России затеяли необычный, но важный проект — Первый международный фестиваль «Мир русского театра», который вот-вот начнется в Италии, в городке Монтекатини Терме, что в Тоскане.",CategoriesList.getInstance().getCategory(1)));
        newsList.add(new News(2, "Писательница Алиса Даншох зовет с собой в путешествие по Флоренции", "Творчество Алисы Даншох весьма разнопланово: после ставших популярными «Кулинарных воспоминаний счастливого детства» и истории «Не совсем святого семейства из Серебряного переулка» в свет вышла уже пятая книга, в основу которой легли культурологические эссе о Флоренции — городе, который стал ярким эпизодом, или даже целой главой, для многих представителей отечественной культуры — от Фонвизина и Достоевского до Бродского и Андрея Тарковского. Посмотрим же на Флоренцию глазами Алисы Даншох.",CategoriesList.getInstance().getCategory(1)));
        newsList.add(new News(3, "Сборная России начала подготовку к Кубку Конфедераций-2017","С момента объявления Станиславом Черчесовым  расширенного состава на Кубок конфедераций сборная уже успела понести три ощутимые потери. Из-за травм национальная команда не досчиталась Андрея Лунева, Алана Дзагоева и Марио Фернандеса. Тренерский штаб нашел замену только третьему вратарю – в срочном порядке был приглашен Владимир Габулов, который не примерял майку сборной более четырех лет.",CategoriesList.getInstance().getCategory(2)));
        newsList.add(new News(4, "Итальянские мастера. В чем секрет Карреры и других тренеров-чемпионов?", "Нельзя говорить об итальянской тренерской школе, не упомянув Коверчано – район Флоренции, где уже на протяжении многих десятилетий проходят подготовку все, кто мечтает посвятить себя этому непростому ремеслу. Марчелло Липпи, Арриго Сакки, Антонио Конте, Клаудио Раньери, Роберто Манчини, Карло Анчелотти, Массимо Каррера  и многие другие специалисты, которых подарила нам Серия A, познавали азы мастерства управления командой именно здесь. За долгие годы местная школа копила знания, традиции, обменивалась опытом с зарубежными коллегами и оттачивала футбольную науку, чтобы на выходе получить самый сильный тренерский корпус в мире.",CategoriesList.getInstance().getCategory(2)));
        */
    }

    //   добавлено (начало)

    private boolean updateInProgress = false;

    public interface OnUpdateListener{
        public void updateStarted();
        public void updateFinished(boolean success);
    }

    private List<OnUpdateListener> listeners = new ArrayList<>();

    public void addListener(OnUpdateListener listener){
        listeners.add(listener);
    }

    public void removeListener(OnUpdateListener listener){
        listeners.remove(listener);
    }

    public void update(int idCategory){     // добавлен параметр метода
        if (updateInProgress){
            return;
        }
        updateInProgress = true;

        for(OnUpdateListener listener : listeners){
            listener.updateStarted();
        }

        Request request = new Request(ServerMethod.NEWSLIST,"GET");
        request.addUrlParameter(Integer.toString(idCategory));      // добавлено
        Server.performRequestAsync(request, new Server.OnRequestListener(){

            @Override
            public void onRequestComplete(Response response) {
                boolean success = true;

                if(response.isSuccessful()){
                    try {
                        List<News> newsList = new ArrayList<News>();
                        JSONObject json = response.getJson();
                        JSONArray array = json.getJSONArray("list");
                        for(int i=0; i < array.length(); ++i){
                            JSONObject obj = array.getJSONObject(i);
                            News news = new News(obj, category);
                            newsList.add(news);
                        }
                        NewsList.this.newsList = newsList;
                    }
                    catch (JSONException e){
                        Log.e("NewsList","Failed to update newslist", e);
                        success = false;
                    }
                }
                else {
                    success = false;
                }
                updateInProgress = false;
                for (OnUpdateListener listener : listeners){
                    listener.updateFinished(success);
                }
            }
        });
    }

    //   добавлено (конец)

    public List<News> getNewsList(){
        return newsList;
    }

    public News getNews(int numberNews){
        for(News news : newsList){
            if (news.getNumber() == numberNews){
                return news;
            }
        }
        return null;
    }

}
