package starter.search;

import net.serenitybdd.screenplay.targets.Target;

class SearchForm {
    static Target SEARCH_FIELD = Target.the("search field").locatedBy("#searchbox_input");

    static Target BING_SEARCH_FIELD = Target.the("search field in Bing").locatedBy("#sb_form_q");

}
