package aldenor.devout_catholic.services;

import aldenor.devout_catholic.config.WebClientConfig;
import aldenor.devout_catholic.model.LiturgiaDiaria;
import aldenor.devout_catholic.model.liturgia.Leitura;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LiturgiaDiariaServices {

    private final WebClientConfig webClientConfig;

    public LiturgiaDiariaServices(WebClientConfig webClientConfig) {
        this.webClientConfig = webClientConfig;
    }

    public LiturgiaDiaria liturgiaDoDia() {
        WebClient webClient = webClientConfig.createWebClient("https://liturgia.up.railway.app");

        Mono<LiturgiaDiaria> responseMono = webClient
                .get()
                .uri("/") 
                .retrieve()
                .bodyToMono(LiturgiaDiaria.class);

        LiturgiaDiaria liturgiaDiaria = responseMono.block(); 

        if (liturgiaDiaria.getSegundaLeitura() instanceof String) {
            String segundaLeituraStr = (String) liturgiaDiaria.getSegundaLeitura();
            // Lógica para tratar quando 'segundaLeitura' é uma string
            // Exemplo: se for "Não há segunda leitura hoje!", você pode deixar um campo vazio ou outro comportamento desejado.
        } else if (liturgiaDiaria.getSegundaLeitura() instanceof Leitura) {
            Leitura segundaLeitura = (Leitura) liturgiaDiaria.getSegundaLeitura();
            // Lógica para tratar quando 'segundaLeitura' é um objeto Leitura
        }

        return liturgiaDiaria;
    }

    public LiturgiaDiaria liturgiaPorData(int dia, int mes) {
        WebClient webClient = webClientConfig.createWebClient("https://liturgia.up.railway.app");

        String endpoint = String.format("/?dia=%d&mes=%d", dia, mes);

        Mono<LiturgiaDiaria> responseMono = webClient
                .get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(LiturgiaDiaria.class);

        LiturgiaDiaria liturgiaDiaria = responseMono.block(); 

        // Verifique se 'segundaLeitura' é uma String ou um objeto 'Leitura'
        if (liturgiaDiaria.getSegundaLeitura() instanceof String) {
            String segundaLeituraStr = (String) liturgiaDiaria.getSegundaLeitura();
            // Lógica para tratar quando 'segundaLeitura' é uma string
        } else if (liturgiaDiaria.getSegundaLeitura() instanceof Leitura) {
            Leitura segundaLeitura = (Leitura) liturgiaDiaria.getSegundaLeitura();
            // Lógica para tratar quando 'segundaLeitura' é um objeto Leitura
        }

        return liturgiaDiaria;
    }
}
