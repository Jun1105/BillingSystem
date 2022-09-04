import { createI18n } from 'vue-i18n';
import ZH from './lang/zh'
import EN from './lang/en'
import elementEnLocale from 'element-plus/es/locale/lang/en'; // element-ui lang
import elementZhLocale from 'element-plus/es/locale/lang/zh-cn';// element-ui lang

const message = {
    zh: {
        ...ZH,
        ...elementZhLocale
    },
    en: {
        ...EN,
        ...elementEnLocale
    },
};

const i18n = createI18n({
    legacy: false,
    globalInjection: true,
    locale: 'zh',
    messages: message,
});

export default i18n;