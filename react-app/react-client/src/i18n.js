import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';
import translationEN from './locales/en/translation.json'
import translationES_AR from './locales/es_AR/translation.json'
import translationES from './locales/es/translation.json'

const detectionOptions = {
    order: ['path', 'cookie', 'navigator', 'localStorage', 'subdomain', 'queryString', 'htmlTag'],
    lookupFromPathIndex: 0
};

const resources = {
    en: {
        translation: translationEN
    },
    es_AR: {
        translation: translationES_AR
    },
    es: {
        translation: translationES
    }
}

i18n.use(LanguageDetector).use(initReactI18next).init({
    resources,
    lng: 'en',
    fallbackLng: 'es',
    interpolation: {
        escapeValue: false
    },
    react: {
        useSuspense: false
    }
});

export default i18n;
