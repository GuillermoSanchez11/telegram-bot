package es.codegym.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static es.codegym.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public static final String NAME = "Pathot_bot";
    public static final String TOKEN = "7237626482:AAFOmZ0UWkjvrlDZKrEMfSE6TFv1dUEl35E";

    // User quiz state tracking
    private final Map<Long, Integer> userQuizState = new HashMap<>();
    private final Map<Long, Integer> userQuizScore = new HashMap<>();

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        // Get the user's ID
        long userId = getUserId(update);

        // Handle start command
        if (update.hasMessage() && update.getMessage().getText().equalsIgnoreCase("/start")) {
            // Reset user's quiz state
            userQuizState.remove(userId);
            userQuizScore.remove(userId);
            sendPhotoMessageAsync("https://p.turbosquid.com/ts-thumb/Zj/UN60Jz/i3/cuterobotcat_0008/jpg/1678510452/1920x1080/fit_q99/62650673d9834e2c4888af9715a7ba3a32626914/cuterobotcat_0008.jpg");
            // Present main menu
            sendTextMessageAsync(WELCOME_TEXT, Map.of(
                    "Explicar conceptos", "EXPLAIN",
                    "Recursos útiles", "RESOURCES",
                    "Cuestionario", "QUIZ"

            ));
            return;
        }

        // Handle callback queries
        if (update.hasCallbackQuery()) {
            handleCallbackQueries(update, userId);
            return;
        }

        // Handle quiz interactions
        if (update.hasMessage()) {
            handleQuizInteractions(update, userId);
        }
    }

    private void handleCallbackQueries(Update update, long userId) {
        String buttonKey = getCallbackQueryButtonKey();
        switch (buttonKey) {
            case "EXPLAIN":
                sendPhotoMessageAsync("https://p.turbosquid.com/ts-thumb/Zj/UN60Jz/La/cuterobotcat_0010/jpg/1678510453/1920x1080/fit_q99/4d7cb0533042f558e81be850675d6743010e05d4/cuterobotcat_0010.jpg");

                // Lista de botones en orden deseado
                List<String[]> buttons = List.of(
                        new String[]{"1. Transformación Digital", "TOPIC_1"},
                        new String[]{"2. Planeación Estratégica de TI", "TOPIC_2"},
                        new String[]{"3. Gobernanza de TI", "TOPIC_3"},
                        new String[]{"4. Infraestructura TI", "TOPIC_4"},
                        new String[]{"5. Negocios Digitales", "TOPIC_5"},
                        new String[]{"6. Revolución Industrial 4.0 y 5.0", "TOPIC_6"},
                        new String[]{"7. Sistemas de Información", "TOPIC_7"},
                        new String[]{"8. Aspectos Éticos y Legales", "TOPIC_8"},
                        new String[]{"Volver al menú principal", "RETURN_MENU"}
                );

                // Crear un mapa ordenado
                Map<String, String> buttonMap = new LinkedHashMap<>();
                for (String[] button : buttons) {
                    buttonMap.put(button[0], button[1]);
                }

                sendTextMessageAsync(TOPICS_TEXT, buttonMap);
                break;


            case "RESOURCES":
                sendPhotoMessageAsync("https://p.turbosquid.com/ts-thumb/Zj/UN60Jz/jQ/cuterobotcat_0001/jpg/1678510450/1920x1080/fit_q99/34189f3b1df9f9794caa75181299b6998baa4735/cuterobotcat_0001.jpg");
                sendTextMessageAsync(RESOURCES_TEXT, Map.of(
                        "Volver al menú principal", "RETURN_MENU"
                ));
                break; // Se añadió el break aquí

            case "QUIZ":
                startQuiz(userId);
                break; // Se añadió el break aquí

            case "RETURN_MENU":
                sendTextMessageAsync(WELCOME_TEXT, Map.of(
                        "Explicar conceptos", "EXPLAIN",
                        "Recursos útiles", "RESOURCES",
                        "Cuestionario", "QUIZ"
                ));
                break; // Se añadió el break aquí

            case "START_QUIZ":
                startQuiz(userId);
                break; // Se añadió el break aquí

            default:
                handleTopicCallbacks(buttonKey);
                break; // Se añadió el break aquí
        }
    }


    private void startQuiz(long userId) {
        // Initialize quiz state and score
        userQuizState.put(userId, QUIZ_STATE_QUESTION_1);
        userQuizScore.put(userId, 0);

        // Send first quiz question
        sendTextMessageAsync(QUIZ_QUESTION_1);
    }

    private void handleQuizInteractions(Update update, long userId) {
        String userResponse = update.getMessage().getText().toLowerCase();
        Integer currentQuizState = userQuizState.get(userId);

        if (currentQuizState == null) {
            // If no active quiz, ignore or return to menu
            return;
        }

        switch (currentQuizState) {
            case QUIZ_STATE_QUESTION_1 -> handleQuizQuestion1(userId, userResponse);
            case QUIZ_STATE_QUESTION_2 -> handleQuizQuestion2(userId, userResponse);
            case QUIZ_STATE_QUESTION_3 -> handleQuizQuestion3(userId, userResponse);
        }
    }

    private void handleQuizQuestion1(long userId, String userResponse) {
        int score = userQuizScore.get(userId);

        if (userResponse.equals("2")) {
            // Correct answer
            score++;
            userQuizScore.put(userId, score);
            sendTextMessageAsync("¡Correcto! La Transformación Digital integra tecnología en todos los aspectos de una organización.");
        } else {
            sendTextMessageAsync("Respuesta incorrecta. La Transformación Digital implica la integración de tecnología digital en todos los aspectos de una organización.");
        }

        // Move to next question
        userQuizState.put(userId, QUIZ_STATE_QUESTION_2);
        sendTextMessageAsync(QUIZ_QUESTION_2);
    }

    private void handleQuizQuestion2(long userId, String userResponse) {
        int score = userQuizScore.get(userId);

        if (userResponse.equals("2")) {
            // Correct answer
            score++;
            userQuizScore.put(userId, score);
            sendTextMessageAsync("¡Correcto! Un principio clave de la Gobernanza de TI es alinear los recursos tecnológicos con los objetivos del negocio.");
        } else {
            sendTextMessageAsync("Respuesta incorrecta. La Gobernanza de TI busca alinear los recursos tecnológicos con los objetivos del negocio.");
        }

        // Move to next question
        userQuizState.put(userId, QUIZ_STATE_QUESTION_3);
        sendTextMessageAsync(QUIZ_QUESTION_3);
    }

    private void handleQuizQuestion3(long userId, String userResponse) {
        int score = userQuizScore.get(userId);

        if (userResponse.equals("2")) {
            // Correct answer
            score++;
            userQuizScore.put(userId, score);
            sendTextMessageAsync("¡Correcto! Un Sistema de Información es un conjunto de herramientas para recopilar, procesar y distribuir información.");
        } else {
            sendTextMessageAsync("Respuesta incorrecta. Un Sistema de Información es un conjunto de herramientas para recopilar, procesar y distribuir información.");
        }

        // Finish quiz
        userQuizState.put(userId, QUIZ_STATE_COMPLETED);

        // Send final message with score
        String finalMessage = String.format(
                "*¡Cuestionario completado!*\n\n" +
                        "Puntuación: %d/3 respuestas correctas\n\n" +
                        "¡Sigue aprendiendo sobre Fundamentos de Sistemas de Información!",
                score
        );
        sendPhotoMessageAsync("https://p.turbosquid.com/ts-thumb/Zj/UN60Jz/d6/cuterobotcat_0009/jpg/1678510452/1920x1080/fit_q99/23593ca196ad0078ac3c7f79706b9acb9c8b43b4/cuterobotcat_0009.jpg");

        sendTextMessageAsync(finalMessage, Map.of(
                "Volver al menú principal", "RETURN_MENU"
        ));
    }

    private void handleTopicCallbacks(String buttonKey) {
        // Existing topic handling logic remains the same as in the previous implementation
        if (buttonKey.startsWith("TOPIC_")) {
            switch (buttonKey) {
                case "TOPIC_1" -> sendTextMessageAsync("""
                        *Transformación Digital:*
                        La transformación digital implica la integración de tecnologías digitales en todos los aspectos de una organización. Esto incluye:
                        - Adopción de nuevas tecnologías (computación en la nube, inteligencia artificial, Internet de las Cosas, etc.)
                        - Digitalización de procesos y servicios
                        - Análisis de datos para tomar mejores decisiones
                        - Crear nuevos modelos de negocio habilitados por tecnología
                        - Mejorar la experiencia del cliente mediante soluciones digitales
                        """, Map.of(
                        "Volver a temas", "EXPLAIN",
                        "Volver al menú principal", "RETURN_MENU"
                ));
                case "TOPIC_2" -> sendTextMessageAsync("""
                        *Planeación Estratégica de TI:*
                        La planeación estratégica de TI consiste en definir cómo los recursos tecnológicos pueden apoyar y alinear con los objetivos del negocio. Esto involucra:
                        - Analizar las necesidades tecnológicas de la organización
                        - Identificar oportunidades de mejora y automatización
                        - Determinar las inversiones y proyectos de TI prioritarios
                        - Asegurar que la tecnología habilite la estrategia y ventaja competitiva
                        - Gestionar adecuadamente los riesgos y la seguridad de la información
                        """, Map.of(
                        "Volver a temas", "EXPLAIN",
                        "Volver al menú principal", "RETURN_MENU"
                ));
                case "TOPIC_3" -> sendTextMessageAsync("""
                        *Gobernanza de TI:*
                        La gobernanza de TI se enfoca en asegurar que la tecnología respalde efectivamente los objetivos y metas del negocio. Incluye:
                        - Establecer políticas, procesos y estructuras de toma de decisiones
                        - Definir roles y responsabilidades para la gestión de TI
                        - Medir y monitorear el desempeño de TI
                        - Alinear las inversiones y proyectos de TI con las prioridades del negocio
                        - Asegurar el cumplimiento de requisitos legales y regulatorios
                        """, Map.of(
                        "Volver a temas", "EXPLAIN",
                        "Volver al menú principal", "RETURN_MENU"
                ));
                case "TOPIC_4" -> sendTextMessageAsync("""
                        *Infraestructura TI:*
                        La infraestructura tecnológica es el conjunto de hardware, software, redes y servicios que permiten el funcionamiento de una organización. Incluye:
                        - Servidores, computadoras, dispositivos móviles
                        - Sistemas operativos, base de datos, aplicaciones
                        - Redes de comunicación (LAN, WAN, Internet)
                        - Servicios en la nube (IaaS, PaaS, SaaS)
                        - Sistemas de almacenamiento y respaldo de información
                        """, Map.of(
                        "Volver a temas", "EXPLAIN",
                        "Volver al menú principal", "RETURN_MENU"
                ));
                case "TOPIC_5" -> sendTextMessageAsync("""
                        *Negocios Digitales:*
                        Los negocios digitales se refieren a las organizaciones que han adoptado tecnologías digitales para transformar su modelo de negocio y crear valor. Algunas características incluyen:
                        - Ofrecer productos y servicios digitales
                        - Utilizar plataformas en línea para llegar a clientes
                        - Aprovechar datos y analítica para la toma de decisiones
                        - Automatizar y optimizar procesos internos
                        - Innovar constantemente con soluciones tecnológicas
                        """, Map.of(
                        "Volver a temas", "EXPLAIN",
                        "Volver al menú principal", "RETURN_MENU"
                ));
                case "TOPIC_6" -> sendTextMessageAsync("""
                        *Revolución Industrial 4.0 y 5.0:*
                        La Revolución Industrial 4.0 y 5.0 se refieren a la transición hacia sistemas de producción y gestión inteligentes, impulsados por tecnologías emergentes como:
                        - Internet de las Cosas (IoT)
                        - Inteligencia Artificial y Aprendizaje Automático
                        - Robótica avanzada y automatización
                        - Computación en la nube y Big Data
                        - Realidad Aumentada y Realidad Virtual
                        - Blockchain y ciberseguridad
                        Esto permite mayor eficiencia, flexibilidad y personalización en la producción y los servicios.
                        """, Map.of(
                        "Volver a temas", "EXPLAIN",
                        "Volver al menú principal", "RETURN_MENU"
                ));
                case "TOPIC_7" -> sendTextMessageAsync("""
                        *Sistemas de Información:*
                        Los sistemas de información son el conjunto de herramientas y procesos que permiten recopilar, procesar, almacenar y distribuir información para apoyar la toma de decisiones y operaciones de una organización. Algunos ejemplos incluyen:
                        - ERP (Enterprise Resource Planning)
                        - CRM (Customer Relationship Management)
                        - MIS (Management Information Systems)
                        - SIG (Sistemas de Información Geográfica)
                        - SGG (Sistemas de Gestión del Conocimiento)
                        """, Map.of(
                        "Volver a temas", "EXPLAIN",
                        "Volver al menú principal", "RETURN_MENU"
                ));
                case "TOPIC_8" -> sendTextMessageAsync("""
                        *Aspectos Éticos y Legales de los Sistemas de Información:*
                        El uso de tecnologías de información plantea diversos desafíos éticos y legales que deben ser considerados, como:
                        - Privacidad y protección de datos personales
                        - Seguridad de la información y ciberseguridad
                        - Propiedad intelectual y derechos de autor
                        - Impacto social y laboral de las tecnologías
                        - Responsabilidad y rendición de cuentas en el uso de TI
                        - Cumplimiento de regulaciones y normas aplicables
                        """, Map.of(
                        "Volver a temas", "EXPLAIN",
                        "Volver al menú principal", "RETURN_MENU"
                ));
                case "RETURN_MENU" -> sendTextMessageAsync(WELCOME_TEXT, Map.of(
                        "Explicar conceptos", "EXPLAIN",
                        "Recursos útiles", "RESOURCES",
                        "Cuestionario", "QUIZ"
                ));
            }
        }
    }

    // Utility method to get user ID from update
    private long getUserId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        return 0;
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}