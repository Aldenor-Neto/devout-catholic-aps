package com.aldenor_neto.devout_catholic.services;

import com.aldenor_neto.devout_catholic.model.biblia.Livro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors; // adicione este import no topo

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BibliaService {

    private List<Livro> carregarBiblia(String version) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json;

            if (version.equalsIgnoreCase("avemaria")) {
                json = "json/catolica - Ave Maria.json";
            } else {
                json = "json/catolica-jerusalem.json";
            }

            ClassPathResource resource = new ClassPathResource(json);

            if (!resource.exists()) {
                System.out.println("Arquivo não encontrado: " + resource.getFilename());
                return null;
            }

            return objectMapper.readValue(
                    resource.getInputStream(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Livro.class));

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Livro> getBiblia(String version) {
        return carregarBiblia(version);
    }

    public Optional<Livro> getLivro(String version, String nomeLivro) {
        List<Livro> biblia = carregarBiblia(version);
        if (biblia == null)
            return Optional.empty();

        return biblia.stream()
                .filter(livro -> livro.getName() != null &&
                        livro.getName().equalsIgnoreCase(nomeLivro))
                .findFirst();
    }

    public Optional<String[]> getCapitulo(String version, String nomeLivro, int capitulo) {
        Optional<Livro> livroOpt = getLivro(version, nomeLivro);
        if (livroOpt.isEmpty())
            return Optional.empty();

        Livro livro = livroOpt.get();
        if (capitulo < 1 || capitulo > livro.getChapters().length)
            return Optional.empty();

        return Optional.of(livro.getChapters()[capitulo - 1]); // Capítulos são base 1
    }

    public Optional<String> getVersiculo(String version, String nomeLivro, int capitulo, int versiculo) {
        Optional<String[]> capituloOpt = getCapitulo(version, nomeLivro, capitulo);
        if (capituloOpt.isEmpty())
            return Optional.empty();

        String[] versiculos = capituloOpt.get();
        if (versiculo < 1 || versiculo > versiculos.length)
            return Optional.empty();

        return Optional.of(versiculos[versiculo - 1]); // Versículos são base 1
    }

    public List<String> listarLivros(String version) {
        List<Livro> biblia = carregarBiblia(version);
        if (biblia == null)
            return List.of();

        return biblia.stream()
                .map(Livro::getName)
                .collect(Collectors.toList());
    }
}
