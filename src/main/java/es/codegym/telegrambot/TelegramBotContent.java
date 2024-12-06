package es.codegym.telegrambot;

public class TelegramBotContent {

    public static final String WELCOME_TEXT = """
            *¡Hola! Soy tu bot de Fundamentos de Sistemas de Información.* 🤖
            
            Estoy aquí para ayudarte a aprender y explorar temas clave relacionados con los sistemas de información. 
            ¿Qué te gustaría hacer hoy? Estas son tus opciones:
            
            1. *Explicar conceptos clave*: Aprende sobre temas como Transformación Digital, Gobernanza de TI, Infraestructura TI y más.
            2. *Recursos de estudio*: Obtén enlaces útiles y materiales para profundizar en los temas.
            3. *Cuestionario*: Pon a prueba tus conocimientos con un pequeño cuestionario interactivo.
            
            Escribe el número o selecciona la opción correspondiente para comenzar.
            """;

    public static final String TOPICS_TEXT = """
            *Temas disponibles para explorar:*
            
            1. Transformación Digital
            2. Planeación Estratégica de TI
            3. Gobernanza de TI
            4. Infraestructura TI
            5. Negocios Digitales
            6. Revolución Industrial 4.0 y 5.0
            7. Sistemas de Información (ERP, CSM, MIS, SIG, SGG)
            8. Aspectos Éticos y Legales de los SI
            
            Escribe el número del tema para aprender más.
            """;

    public static final String RESOURCES_TEXT = """
            *Enlaces útiles:*
            
            - [Guía de Transformación Digital](https://www.sap.com/latinamerica/insights/what-is-digital-transformation.html)
            - [Introducción a la Gobernanza de TI](https://www.ibm.com/mx-es/think/topics/it-governance)
            - [Conceptos de Infraestructura TI](https://www.redhat.com/es/topics/cloud-computing/what-is-it-infrastructure)
            - [Sistemas ERP explicados](https://www.sap.com/latinamerica/products/erp/what-is-erp.html)
            
            *Videos útiles:*
            
            - [Canal de divulgación de IA](https://www.youtube.com/@DotCSV)
            - [Como hacer un buen pitch](https://www.youtube.com/watch?v=2b3xG_YjgvI)
            
            ¡Explora estos enlaces para más información detallada!
            """;

    public static final String QUIZ_INTRO_TEXT = """
            *¿Listo para el cuestionario?* 🎓
            
            Responde preguntas clave para poner a prueba tu conocimiento sobre los temas principales de la materia.
            
            ¿Comenzamos?
            """;

    public static final String QUIZ_QUESTION_1 = """
            *Pregunta 1:*
            
            ¿Qué describe mejor la Transformación Digital?
            
            1. La adopción de computadoras en oficinas.
            2. La integración de tecnología digital en todos los aspectos de una organización.
            3. La compra de hardware moderno.
            
            Escribe el número de tu respuesta.
            """;

    public static final String QUIZ_QUESTION_2 = """
            *Pregunta 2:*
            
            ¿Cuál de los siguientes es un principio de Gobernanza de TI?
            
            1. Maximizar el uso de software gratuito.
            2. Alinear los recursos tecnológicos con los objetivos del negocio.
            3. Comprar la infraestructura más cara.
            
            Escribe el número de tu respuesta.
            """;

    public static final String QUIZ_QUESTION_3 = """
            *Pregunta 3:*
            
            ¿Qué es un Sistema de Información (SI)?
            
            1. Un dispositivo de hardware.
            2. Un conjunto de herramientas para recopilar, procesar y distribuir información.
            3. Un tipo de software de entretenimiento.
            
            Escribe el número de tu respuesta.
            """;

    public static final String QUIZ_FINISH_TEXT = """
            *¡Bien hecho!*
            
            Has completado el cuestionario. Sigue explorando y aprendiendo sobre los *Fundamentos de Sistemas de Información*.
            
            Escribe "Menú" para regresar al menú principal.
            """;

    // Quiz tracking constants
    public static final int QUIZ_STATE_NOT_STARTED = 0;
    public static final int QUIZ_STATE_QUESTION_1 = 1;
    public static final int QUIZ_STATE_QUESTION_2 = 2;
    public static final int QUIZ_STATE_QUESTION_3 = 3;
    public static final int QUIZ_STATE_COMPLETED = 4;
}