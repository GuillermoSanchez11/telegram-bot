package es.codegym.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static es.codegym.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public static final String NAME = "Pathot_bot";
    public static final String TOKEN = "7237626482:AAFOmZ0UWkjvrlDZKrEMfSE6TFv1dUEl35E";

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            setUserGlory(0);
            sendTextMessageAsync(STEP_1_TEXT, Map.of(
                    "Leer más sobre transformación digital", "Botón 1",
                    "Realizar cuestionario", "Botón 2"
            ));
        }

        if (update.hasCallbackQuery()) {
            String buttonKey = getCallbackQueryButtonKey();
            switch (buttonKey) {
                case "Botón 1" -> {
                    // Acción: Mostrar más información
                    String infoText = """
                            *Más sobre Transformación Digital:*
                            - Impulsa la innovación y mejora la eficiencia.
                            - Facilita la experiencia del cliente.
                            - Aumenta la competitividad de las empresas.
                            
                            ¡Prepárate para avanzar al siguiente nivel!
                            """;
                    sendTextMessageAsync(infoText);
                    sendTextMessageAsync(STEP_2_TEXT, Map.of(
                            "Explorar ejemplos", "Botón 3",
                            "Resolver caso práctico", "Botón 4"
                    ));
                }
                case "Botón 2" -> {
                    // Acción: Realizar cuestionario
                    String question = """
                            *Cuestionario:*
                            Pregunta 1: ¿Qué significa transformación digital?
                            a) Cambiar dispositivos físicos por digitales.
                            b) Integrar tecnología para mejorar procesos.
                            c) Crear aplicaciones para clientes.
                            
                            Escribe la letra de tu respuesta:
                            """;
                    sendTextMessageAsync(question);
                }
                case "Botón 3" -> {
                    // Acción: Explorar ejemplos
                    String exampleText = """
                            *Ejemplo de Planeación Estratégica de TI:*
                            Una empresa de transporte implementa IoT para rastrear su flota, 
                            mejorando la logística y reduciendo costos.
                            
                            ¡Ahora avanza al siguiente nivel!
                            """;
                    sendTextMessageAsync(exampleText);
                    sendTextMessageAsync(STEP_3_TEXT, Map.of(
                            "Estudiar principios de gobernanza de TI", "Botón 5",
                            "Participar en debate simulado", "Botón 6"
                    ));
                }
                case "Botón 4" -> {
                    // Acción: Resolver caso práctico
                    String caseStudy = """
                            *Caso Práctico:*
                            Una tienda quiere implementar un ERP para gestionar inventarios.
                            Pregunta: ¿Qué módulo recomendarías y por qué?
                            
                            Responde escribiendo tu opinión:
                            """;
                    sendTextMessageAsync(caseStudy);
                }
                case "Botón 5" -> {
                    // Acción: Estudiar principios de gobernanza de TI
                    String principlesText = """
                            *Principios de Gobernanza de TI:*
                            - Alineación de TI con los objetivos empresariales.
                            - Gestión eficiente de recursos tecnológicos.
                            - Monitoreo constante del desempeño.
                            
                            ¡Sigue aprendiendo y avanza al siguiente paso!
                            """;
                    sendTextMessageAsync(principlesText);
                    sendTextMessageAsync(FINAL_TEXT);
                }
                case "Botón 6" -> {
                    // Acción: Participar en debate simulado
                    String debateText = """
                            *Debate Simulado:*
                            Imagina que eres parte del equipo de gobernanza de TI.
                            ¿Cómo priorizarías los proyectos tecnológicos en la empresa?
                            
                            Comparte tu respuesta y continúa aprendiendo.
                            """;
                    sendTextMessageAsync(debateText);
                    sendTextMessageAsync(FINAL_TEXT);
                }
                default -> sendTextMessageAsync("Opción no reconocida. Por favor, inténtalo de nuevo.");
            }
        }

        // Procesar respuestas abiertas (por ejemplo, cuestionarios y casos prácticos)
        if (update.hasMessage() && !update.getMessage().getText().equals("/start")) {
            String userResponse = update.getMessage().getText().toLowerCase();
            String feedback;
            if (userResponse.equals("b")) {
                feedback = "¡Correcto! Transformación digital significa integrar tecnología para mejorar procesos.";
            } else {
                feedback = "Respuesta incorrecta. La transformación digital implica integrar tecnología para mejorar procesos.";
            }
            sendTextMessageAsync(feedback);
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
